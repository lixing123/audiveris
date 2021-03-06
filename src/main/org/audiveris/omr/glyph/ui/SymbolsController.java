//------------------------------------------------------------------------------------------------//
//                                                                                                //
//                               S y m b o l s C o n t r o l l e r                                //
//                                                                                                //
//------------------------------------------------------------------------------------------------//
// <editor-fold defaultstate="collapsed" desc="hdr">
//
//  Copyright © Hervé Bitteur and others 2000-2017. All rights reserved.
//
//  This program is free software: you can redistribute it and/or modify it under the terms of the
//  GNU Affero General Public License as published by the Free Software Foundation, either version
//  3 of the License, or (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
//  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//  See the GNU Affero General Public License for more details.
//
//  You should have received a copy of the GNU Affero General Public License along with this
//  program.  If not, see <http://www.gnu.org/licenses/>.
//------------------------------------------------------------------------------------------------//
// </editor-fold>
package org.audiveris.omr.glyph.ui;

import org.audiveris.omr.glyph.Glyph;
import org.audiveris.omr.glyph.SymbolsModel;
import org.audiveris.omr.score.TimeRational;
import org.audiveris.omr.script.RationalTask;
import org.audiveris.omr.script.SegmentTask;
import org.audiveris.omr.script.SlurTask;
import org.audiveris.omr.script.TextTask;
import org.audiveris.omr.text.TextRole;

import org.jdesktop.application.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.Collection;

/**
 * Class {@code SymbolsController} is a GlyphsController specifically meant for symbol
 * glyphs, adding handling for assigning Texts, for fixing Slurs and for segmenting on
 * Stems.
 *
 * @author Hervé Bitteur
 */
public class SymbolsController
        extends GlyphsController
{
    //~ Static fields/initializers -----------------------------------------------------------------

    private static final Logger logger = LoggerFactory.getLogger(SymbolsController.class);

    /** Color for hiding unknown glyphs when filter is ON */
    public static final Color hiddenColor = Color.white;

    //~ Constructors -------------------------------------------------------------------------------
    /**
     * Create a handler dedicated to symbol glyphs
     *
     * @param model the related glyphs model
     */
    public SymbolsController (SymbolsModel model)
    {
        super(model);
    }

    //~ Methods ------------------------------------------------------------------------------------
    //----------------------//
    // asyncAssignRationals //
    //----------------------//
    /**
     * Asynchronously assign a rational value to a collection of glyphs with
     * CUSTOM_TIME_SIGNATURE shape
     *
     * @param glyphs       the impacted glyphs
     * @param timeRational the time sig rational value
     * @return the task that carries out the processing
     */
    public Task<Void, Void> asyncAssignRationals (Collection<Glyph> glyphs,
                                                  TimeRational timeRational)
    {
        return new RationalTask(sheet, timeRational, glyphs).launch(sheet);
    }

    //------------------//
    // asyncAssignTexts //
    //------------------//
    /**
     * Asynchronously assign text characteristics to a collection of textual
     * glyphs
     *
     * @param glyphs      the impacted glyphs
     * @param role        the role of this textual element
     * @param textContent the content as a string (if not empty)
     * @return the task that carries out the processing
     */
    public Task<Void, Void> asyncAssignTexts (Collection<Glyph> glyphs,
                                              TextRole role,
                                              String textContent)
    {
        return new TextTask(sheet, role, textContent, glyphs).launch(sheet);
    }

    //--------------//
    // asyncSegment //
    //--------------//
    /**
     * Asynchronously segment a set of glyphs on their stems
     *
     * @param glyphs glyphs to segment in order to retrieve stems
     * @return the task that carries out the processing
     */
    public Task<Void, Void> asyncSegment (Collection<Glyph> glyphs)
    {
        return new SegmentTask(sheet, glyphs).launch(sheet);
    }

    //----------------//
    // asyncTrimSlurs //
    //----------------//
    /**
     * Asynchronously fix a collection of glyphs as large slurs
     *
     * @param glyphs the slur glyphs to fix
     * @return the task that carries out the processing
     */
    public Task<Void, Void> asyncTrimSlurs (Collection<Glyph> glyphs)
    {
        return new SlurTask(sheet, glyphs).launch(sheet);
    }

    //----------//
    // getModel //
    //----------//
    /**
     * Report the underlying model
     *
     * @return the underlying glyphs model
     */
    @Override
    public SymbolsModel getModel ()
    {
        return (SymbolsModel) model;
    }

    //----------//
    // toString //
    //----------//
    @Override
    public String toString ()
    {
        return getClass().getSimpleName();
    }
}
