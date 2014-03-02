package org.protege.swrlapi.xsd;

import org.protege.swrlapi.exceptions.SQWRLLiteralException;
import org.semanticweb.owlapi.vocab.XSDVocabulary;

public class XSDDateTime extends XSDType
{
	public XSDDateTime(String content) throws SQWRLLiteralException
	{
		super(content);

		setURI(XSDVocabulary.DATE_TIME.getIRI());
	}

	public XSDDateTime(java.util.Date date) throws SQWRLLiteralException
	{
		super(XSDTimeUtil.utilDate2XSDDateTimeString(date));

		setURI(XSDVocabulary.DATE_TIME.getIRI());
	}

	@Override
	protected void validate() throws SQWRLLiteralException
	{
		if (getContent() == null)
			throw new SQWRLLiteralException("null content for xsd:DateTime");

		if (!XSDTimeUtil.isValidXSDDateTime(getContent()))
			throw new SQWRLLiteralException("invalid xsd:DateTime " + getContent());
	}
}
