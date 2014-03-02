package org.protege.swrlapi.ext;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDatatype;

public interface OWLDatatypeFactory
{
	OWLDatatype getOWLDatatype(IRI iri);

	OWLDatatype getOWLBooleanDatatype();

	OWLDatatype getOWLShortDatatype();

	OWLDatatype getOWLDoubleDatatype();

	OWLDatatype getOWLFloatDatatype();

	OWLDatatype getOWLIntegerDatatype();

	OWLDatatype getOWLLongDatatype();

	OWLDatatype getOWLStringDatatype();

	OWLDatatype getOWLByteDatatype();

	OWLDatatype getOWLURIDatatype();

	OWLDatatype getOWLDateDatatype();

	OWLDatatype getOWLTimeDatatype();

	OWLDatatype getOWLDateTimeDatatype();

	OWLDatatype getOWLDurationDatatype();
}
