//----------------------------------------------------------------------------//
//                                                                            //
//                    P a i n t i n g P a r a m e t e r s                     //
//                                                                            //
//----------------------------------------------------------------------------//
// <editor-fold defaultstate="collapsed" desc="hdr">                          //
//  Copyright (C) Herve Bitteur 2000-2010. All rights reserved.               //
//  This software is released under the GNU General Public License.           //
//  Goto http://kenai.com/projects/audiveris to report bugs or suggestions.   //
//----------------------------------------------------------------------------//
// </editor-fold>
package omr.score.ui;

import omr.constant.Constant;
import omr.constant.ConstantSet;

import omr.log.Logger;

import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Action;

import java.awt.event.ActionEvent;

/**
 * Class <code>PaintingParameters</code> handles the dynamic parameters related
 * to the painting of any score (slots, voices and marks)
 *
 * @author Hervé Bitteur
 */
public class PaintingParameters
    extends AbstractBean
{
    //~ Static fields/initializers ---------------------------------------------

    /** Specific application parameters */
    private static final Constants constants = new Constants();

    /** Usual logger utility */
    private static final Logger logger = Logger.getLogger(
        PaintingParameters.class);

    /** Should the annotations be painted */
    public static final String ANNOTATION_PAINTING = "annotationPainting";

    /** Should the marks be painted */
    public static final String MARK_PAINTING = "markPainting";

    /** Should the slots be painted */
    public static final String SLOT_PAINTING = "slotPainting";

    /** Should the voices be painted */
    public static final String VOICE_PAINTING = "voicePainting";

    /** A global property name for layers */
    public static final String LAYER_PAINTING = "layerPainting";

    //~ Instance fields --------------------------------------------------------

    /** Layer painting is chosen to be not persistent */
    private PaintingLayer paintingLayer = PaintingLayer.INPUT_OUTPUT;

    //~ Methods ----------------------------------------------------------------

    //-------------//
    // getInstance //
    //-------------//
    public static PaintingParameters getInstance ()
    {
        return Holder.INSTANCE;
    }

    //-----------------------//
    // setAnnotationPainting //
    //-----------------------//
    public void setAnnotationPainting (boolean value)
    {
        boolean oldValue = constants.annotationPainting.getValue();
        constants.annotationPainting.setValue(value);
        firePropertyChange(
            ANNOTATION_PAINTING,
            oldValue,
            constants.annotationPainting.getValue());
    }

    //----------------------//
    // isAnnotationPainting //
    //----------------------//
    public boolean isAnnotationPainting ()
    {
        return constants.annotationPainting.getValue();
    }

    //-----------------//
    // isInputPainting //
    //-----------------//
    public boolean isInputPainting ()
    {
        return (getPaintingLayer() == PaintingLayer.INPUT) ||
               (getPaintingLayer() == PaintingLayer.INPUT_OUTPUT);
    }

    //-----------------//
    // setMarkPainting //
    //-----------------//
    public void setMarkPainting (boolean value)
    {
        boolean oldValue = constants.markPainting.getValue();
        constants.markPainting.setValue(value);
        firePropertyChange(
            MARK_PAINTING,
            oldValue,
            constants.markPainting.getValue());
    }

    //----------------//
    // isMarkPainting //
    //----------------//
    public boolean isMarkPainting ()
    {
        return constants.markPainting.getValue();
    }

    //------------------//
    // isOutputPainting //
    //------------------//
    public boolean isOutputPainting ()
    {
        return (getPaintingLayer() == PaintingLayer.OUTPUT) ||
               (getPaintingLayer() == PaintingLayer.INPUT_OUTPUT);
    }

    //------------------//
    // setPaintingLayer //
    //------------------//
    public void setPaintingLayer (PaintingLayer value)
    {
        PaintingLayer oldValue = getPaintingLayer();
        /// constants.paintingLayer.setValue(value);
        paintingLayer = value;
        firePropertyChange(LAYER_PAINTING, oldValue, getPaintingLayer());
    }

    //------------------//
    // getPaintingLayer //
    //------------------//
    public PaintingLayer getPaintingLayer ()
    {
        /// return constants.paintingLayer.getValue();
        return paintingLayer;
    }

    //-----------------//
    // setSlotPainting //
    //-----------------//
    public void setSlotPainting (boolean value)
    {
        boolean oldValue = constants.slotPainting.getValue();
        constants.slotPainting.setValue(value);
        firePropertyChange(
            SLOT_PAINTING,
            oldValue,
            constants.slotPainting.getValue());
    }

    //----------------//
    // isSlotPainting //
    //----------------//
    public boolean isSlotPainting ()
    {
        return constants.slotPainting.getValue();
    }

    //------------------//
    // setVoicePainting //
    //------------------//
    public void setVoicePainting (boolean value)
    {
        boolean oldValue = constants.voicePainting.getValue();
        constants.voicePainting.setValue(value);
        firePropertyChange(
            VOICE_PAINTING,
            oldValue,
            constants.voicePainting.getValue());
    }

    //-----------------//
    // isVoicePainting //
    //-----------------//
    public boolean isVoicePainting ()
    {
        return constants.voicePainting.getValue();
    }

    //--------------//
    // switchLayers //
    //--------------//
    /**
     * Action that swiches among layer combinations
     * @param e the event that triggered this action
     */
    @Action
    public void switchLayers (ActionEvent e)
    {
        int oldOrd = getPaintingLayer()
                         .ordinal();
        int ord = (oldOrd + 1) % PaintingLayer.values().length;
        setPaintingLayer(PaintingLayer.values()[ord]);
    }

    //-------------------//
    // toggleAnnotations //
    //-------------------//
    /**
     * Action that toggles the display of annotations in the score
     * @param e the event that triggered this action
     */
    @Action(selectedProperty = ANNOTATION_PAINTING)
    public void toggleAnnotations (ActionEvent e)
    {
    }

    //-------------//
    // toggleMarks //
    //-------------//
    /**
     * Action that toggles the display of computed marks in the score
     * @param e the event that triggered this action
     */
    @Action(selectedProperty = MARK_PAINTING)
    public void toggleMarks (ActionEvent e)
    {
    }

    //-------------//
    // toggleSlots //
    //-------------//
    /**
     * Action that toggles the display of vertical time slots
     * @param e the event that triggered this action
     */
    @Action(selectedProperty = SLOT_PAINTING)
    public void toggleSlots (ActionEvent e)
    {
    }

    //--------------//
    // toggleVoices //
    //--------------//
    /**
     * Action that toggles the display of voices with specific colors
     * @param e the event that triggered this action
     */
    @Action(selectedProperty = VOICE_PAINTING)
    public void toggleVoices (ActionEvent e)
    {
    }

    //~ Inner Classes ----------------------------------------------------------

    //-----------//
    // Constants //
    //-----------//
    private static final class Constants
        extends ConstantSet
    {
        //~ Instance fields ----------------------------------------------------

        /** Should the annotations be painted */
        final Constant.Boolean annotationPainting = new Constant.Boolean(
            true,
            "Should the annotations be painted");

        /** Should the slots be painted */
        final Constant.Boolean slotPainting = new Constant.Boolean(
            true,
            "Should the slots be painted");

        /** Should the voices be painted */
        final Constant.Boolean voicePainting = new Constant.Boolean(
            false,
            "Should the voices be painted");

        /** Should the marks be painted */
        final Constant.Boolean markPainting = new Constant.Boolean(
            true,
            "Should the marks be painted");

        /** Should the dummy parts be painted */
        final Constant.Boolean dummyPainting = new Constant.Boolean(
            true,
            "Should the dummy parts be painted");

        //        /** Which layers should be painted */
        //        final PaintingLayer.Constant paintingLayer = new PaintingLayer.Constant(
        //            PaintingLayer.INPUT_OUTPUT,
        //            "Which layers should be painted");
    }

    //--------//
    // Holder //
    //--------//
    private static class Holder
    {
        //~ Static fields/initializers -----------------------------------------

        public static final PaintingParameters INSTANCE = new PaintingParameters();
    }
}
