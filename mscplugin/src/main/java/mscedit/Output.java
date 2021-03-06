package mscedit;

import org.jdom.*;

public class Output extends InputOrOutput {

    Output( Element p_elem ) {
	
	o_xml = p_elem;
    }

    public void apply( Visitor v ) {

	if( ! isNegative() ) {
	    v.caseAOutput( this );
	}
    }
    
    /**
     * set the weight
     * @param weight the weight
     * */
    //public void setWeight(double weight)
    //{
    //    Element x_elem = new Element("weight");
    //    x_elem.addContent(Double.toString(weight));
    //    o_xml.addContent(x_elem);
    //}
    
    /**
     * get the associated weight
     * @return the weight
     * */
    //public double getWeight()
    //{
    //    return Double.parseDouble((o_xml.getChild( "weight" ).getText()));
    //}
    
    /**
     * @return true iff output has a weight
     * */
    //public boolean hasWeight()
    //{
    //    return o_xml.getChild("weight") != null;
    //}
    
}
