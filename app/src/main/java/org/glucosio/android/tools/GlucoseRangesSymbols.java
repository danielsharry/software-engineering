/*
 * Copyright (C) 2016 Glucosio Foundation
 *
 * This file is part of Glucosio.
 *
 * Glucosio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Glucosio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Glucosio.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package org.glucosio.android.tools;

import android.content.Context;

import org.glucosio.android.R;
import org.glucosio.android.db.DatabaseHandler;

public class GlucoseRangesSymbols {

    private DatabaseHandler dB;
    private Context mContext;
    private String preferredRange;
    private int customMin;
    private int customMax;

    public GlucoseRangesSymbols(Context context) {
        this.mContext = context;
        dB = new DatabaseHandler(mContext);
        this.preferredRange = dB.getUser(1).getPreferred_range();
        if (preferredRange.equals("Custom range")) {
            this.customMin = dB.getUser(1).getCustom_range_min();
            this.customMax = dB.getUser(1).getCustom_range_max();
        }
    }

    public String symbolFromReading(int reading) {
        // Check for Hypo/Hyperglycemia
        if (reading < 70) {
            return "▼";
        } else if (reading > 200) {
            return "▲";
        } else if (reading > 70 | reading < 200) {
            // if not check with custom ranges
            switch (preferredRange) {
                case "ADA":
                    if (reading >= 70 & reading <= 180) {
                        return "●";
                    } else if (reading < 70) {
                        return "▽";
                    } else if (reading > 180) {
                        return "△";
                    }
                case "AACE":
                    if (reading >= 110 & reading <= 140) {
                        return "●";
                    } else if (reading < 110) {
                        return "▽";
                    } else if (reading > 140) {
                        return "△";
                    }
                case "UK NICE":
                    if (reading >= 72 & reading <= 153) {
                        return "●";
                    } else if (reading < 72) {
                        return "▽";
                    } else if (reading > 153) {
                        return "△";
                    }
                default:
                    if (reading >= customMin & reading <= customMax) {
                        return "●";
                    } else if (reading < customMin) {
                        return "▽";
                    } else if (reading > customMax) {
                        return "△";
                    }
            }
        }
        return "▲";
    }

    public int stringToColor(String color) {
        switch (color) {
            case "green":
                return mContext.getResources().getColor(R.color.glucosio_reading_ok);
            case "red":
                return mContext.getResources().getColor(R.color.glucosio_reading_hyper);
            case "blue":
                return mContext.getResources().getColor(R.color.glucosio_reading_low);
            case "orange":
                return mContext.getResources().getColor(R.color.glucosio_reading_high);
            default:
                return mContext.getResources().getColor(R.color.glucosio_reading_hypo);
        }
    }

    ;
}
