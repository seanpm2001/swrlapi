package org.protege.swrlapi.ext.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.protege.swrlapi.core.arguments.SWRLBuiltInArgument;
import org.protege.swrlapi.ext.SWRLAPIBuiltInAtom;
import org.protege.swrlapi.ext.SWRLAPIRule;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;

class DefaultSWRLAPIRule implements SWRLAPIRule
{
	private static final long serialVersionUID = 4399366884810154866L;

	private final String ruleName;
	private List<SWRLAtom> bodyAtoms; // Not final because it can be reorganized during processing
	private final List<SWRLAtom> headAtoms;

	public DefaultSWRLAPIRule(String ruleName, List<? extends SWRLAtom> bodyAtoms, List<? extends SWRLAtom> headAtoms)
	{
		this.ruleName = ruleName;
		this.bodyAtoms = new ArrayList<SWRLAtom>(bodyAtoms);
		this.headAtoms = new ArrayList<SWRLAtom>(headAtoms);

		processUnboundBuiltInArguments();
	}

	@Override
	public String getName()
	{
		return this.ruleName;
	}

	@Override
	public List<SWRLAtom> getHeadAtoms()
	{
		return this.headAtoms;
	}

	@Override
	public List<SWRLAtom> getBodyAtoms()
	{
		return this.bodyAtoms;
	}

	@Override
	public String toDisplayText()
	{
		return this.ruleName;
	}

	@Override
	public String toString()
	{
		return this.ruleName + ": " + getRuleText();
	}

	public String getRuleText()
	{
		String result = "";
		boolean isFirst = true;

		for (SWRLAtom atom : getBodyAtoms()) {
			if (!isFirst)
				result += " ^ ";
			result += "" + atom;
			isFirst = false;
		}

		result += " -> ";

		isFirst = true;
		for (SWRLAtom atom : getHeadAtoms()) {
			if (!isFirst)
				result += " ^ ";
			result += "" + atom;
			isFirst = false;
		}
		return result;
	}

	@Override
	public List<SWRLAPIBuiltInAtom> getBuiltInAtomsFromHead(Set<String> builtInNames)
	{
		return getBuiltInAtoms(getHeadAtoms(), builtInNames);
	}

	@Override
	public List<SWRLAPIBuiltInAtom> getBuiltInAtomsFromBody(Set<String> builtInNames)
	{
		return getBuiltInAtoms(getBodyAtoms(), builtInNames);
	}

	private void setBodyAtoms(List<SWRLAtom> atoms)
	{
		this.bodyAtoms = atoms;
	}

	/**
	 * Find all built-in atoms with unbound arguments and tell them which of their arguments are unbound. See <a
	 * href="http://protege.cim3.net/cgi-bin/wiki.pl?SWRLBuiltInBridge#nid88T">here</a> for a discussion of the role of
	 * this method.
	 */
	private void processUnboundBuiltInArguments()
	{
		List<SWRLAPIBuiltInAtom> bodyBuiltInAtoms = new ArrayList<SWRLAPIBuiltInAtom>();
		List<SWRLAtom> bodyNonBuiltInAtoms = new ArrayList<SWRLAtom>();
		List<SWRLAtom> finalBodyAtoms = new ArrayList<SWRLAtom>();
		Set<String> variableNamesUsedByNonBuiltInBodyAtoms = new HashSet<String>(); // By definition, these will always be
																																								// bound.
		Set<String> variableNamesBoundByBuiltIns = new HashSet<String>(); // Names of variables bound by built-ins in this
																																			// rule

		// Process the body atoms and build up list of (1) built-in body atoms, and (2) the variables used by non-built body
		// in atoms.
		for (SWRLAtom atom : getBodyAtoms()) {
			if (atom instanceof SWRLAPIBuiltInAtom)
				bodyBuiltInAtoms.add((SWRLAPIBuiltInAtom)atom);
			else {
				bodyNonBuiltInAtoms.add(atom);
				variableNamesUsedByNonBuiltInBodyAtoms.addAll(atom.getReferencedVariableNames());
			}
		}

		// Process the body built-in atoms and determine if they bind any of their arguments.
		for (SWRLAPIBuiltInAtom builtInAtom : bodyBuiltInAtoms) { // Read through built-in arguments and determine which
																															// are unbound.
			for (SWRLBuiltInArgument argument : builtInAtom.getBuiltInArguments()) {
				if (argument.isVariable()) {
					String argumentVariableName = argument.getVariableName();

					// If a variable argument is not used by any non built-in body atom or is not bound by another body built-in
					// atom it will therefore be
					// unbound when this built-in is called. We thus set this built-in argument to unbound. If a built-in binds an
					// argument, all later
					// built-ins (proceeding from left to right) will be passed the bound value of this variable during rule
					// execution.
					if (!variableNamesUsedByNonBuiltInBodyAtoms.contains(argumentVariableName)
							&& !variableNamesBoundByBuiltIns.contains(argumentVariableName)) {
						argument.setUnbound(); // Tell the built-in that it is expected to bind this argument.
						variableNamesBoundByBuiltIns.add(argumentVariableName); // Flag this as a bound variable for later
																																		// built-ins.
					}
				}
			}
		}
		// If we have built-in atoms, construct a new body with built-in atoms moved to the end of the list. Some rule
		// engines (e.g., Jess) expect variables used as parameters to functions to have been defined before their use in
		// a left to right fashion.
		finalBodyAtoms = processBodyNonBuiltInAtoms(bodyNonBuiltInAtoms);
		setBodyAtoms(finalBodyAtoms);
		finalBodyAtoms.addAll(bodyBuiltInAtoms);
	}

	/**
	 * Build up a list of body class atoms and non class, non built-in atoms.
	 */
	private List<SWRLAtom> processBodyNonBuiltInAtoms(List<SWRLAtom> bodyNonBuiltInAtoms)
	{
		List<SWRLAtom> bodyClassAtoms = new ArrayList<SWRLAtom>();
		List<SWRLAtom> bodyNonClassNonBuiltInAtoms = new ArrayList<SWRLAtom>();
		List<SWRLAtom> result = new ArrayList<SWRLAtom>();

		for (SWRLAtom atom : bodyNonBuiltInAtoms) {
			if (atom instanceof SWRLClassAtom)
				bodyClassAtoms.add(atom);
			else
				bodyNonClassNonBuiltInAtoms.add(atom);
		}

		result.addAll(bodyClassAtoms); // We arrange the class atoms first.
		result.addAll(bodyNonClassNonBuiltInAtoms);

		return result;
	}

	private List<SWRLAPIBuiltInAtom> getBuiltInAtoms(List<SWRLAtom> atoms, Set<String> builtInNames)
	{
		List<SWRLAPIBuiltInAtom> result = new ArrayList<SWRLAPIBuiltInAtom>();

		for (SWRLAtom atom : atoms) {
			if (atom instanceof SWRLAPIBuiltInAtom) {
				SWRLAPIBuiltInAtom builtInAtom = (SWRLAPIBuiltInAtom)atom;
				if (builtInNames.contains(builtInAtom.getBuiltInPrefixedName()))
					result.add(builtInAtom);
			}
		}
		return result;
	}
}
