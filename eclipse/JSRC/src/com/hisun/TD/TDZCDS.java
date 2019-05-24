package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

import java.io.IOException;
import java.sql.SQLException;

public class TDZCDS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_AP_MMO = "TD";
    int WS_B_DATE = 0;
    int WS_E_DATE = 0;
    short WS_GIV = 0;
    short WS_REMA = 0;
    short WS_REMB = 0;
    short WS_LEAP = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    TDCCDS TDCCDS;
    public void MP(SCCGWA SCCGWA, TDCCDS TDCCDS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCCDS = TDCCDS;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, TDCCDS.BEG_DATE);
        CEP.TRC(SCCGWA, TDCCDS.END_DATE);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCCDS.RC_MSG.RC);
        CEP.TRC(SCCGWA, "TDZCDS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCCDS.BEG_DATE > TDCCDS.END_DATE) {
            IBS.CPY2CLS(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT, TDCCDS.RC_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_B_DATE = TDCCDS.BEG_DATE;
        WS_E_DATE = TDCCDS.END_DATE;
        R000_GET_CDS_DAYS();
        if (pgmRtn) return;
        R000_GET_CDS_MONS();
        if (pgmRtn) return;
        TDCCDS.BEG_DATE = WS_B_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.BEG_DATE+"", TDCCDS.REDEFINES6);
        TDCCDS.END_DATE = WS_E_DATE;
        IBS.CPY2CLS(SCCGWA, TDCCDS.END_DATE+"", TDCCDS.REDEFINES11);
    }
    public void R000_GET_CDS_DAYS() throws IOException,SQLException,Exception {
        if (TDCCDS.REDEFINES6.BEG_DATE_DD == 31) {
            TDCCDS.REDEFINES6.BEG_DATE_DD = 30;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.REDEFINES6);
            TDCCDS.BEG_DATE = Integer.parseInt(JIBS_tmp_str[0]);
        }
        if (TDCCDS.REDEFINES11.END_DATE_DD == 31) {
            TDCCDS.REDEFINES11.END_DATE_DD = 30;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.REDEFINES11);
            TDCCDS.END_DATE = Integer.parseInt(JIBS_tmp_str[0]);
        }
        R000_GET_BEG_DD();
        if (pgmRtn) return;
        R000_GET_END_DD();
        if (pgmRtn) return;
        TDCCDS.DAYS = ( TDCCDS.REDEFINES11.END_DATE_YY - TDCCDS.REDEFINES6.BEG_DATE_YY ) * 360 + ( TDCCDS.REDEFINES11.END_DATE_MM - TDCCDS.REDEFINES6.BEG_DATE_MM ) * 30 + ( TDCCDS.REDEFINES11.END_DATE_DD - TDCCDS.REDEFINES6.BEG_DATE_DD );
    }
    public void R000_GET_BEG_DD() throws IOException,SQLException,Exception {
        if (TDCCDS.REDEFINES6.BEG_DATE_MM == 2 
            && (TDCCDS.OPT == 'O' 
            || TDCCDS.OPT == 'A' 
            || TDCCDS.OPT == 'C')) {
            if (TDCCDS.REDEFINES6.BEG_DATE_DD == 28) {
                WS_REMA = (short) (TDCCDS.REDEFINES6.BEG_DATE_YY % 400);
                WS_GIV = (short) ((TDCCDS.REDEFINES6.BEG_DATE_YY - WS_REMA) / 400);
                if (WS_REMA == 0) {
                    WS_LEAP = 1;
                } else {
                    WS_REMA = (short) (TDCCDS.REDEFINES6.BEG_DATE_YY % 4);
                    WS_GIV = (short) ((TDCCDS.REDEFINES6.BEG_DATE_YY - WS_REMA) / 4);
                    WS_REMB = (short) (TDCCDS.REDEFINES6.BEG_DATE_YY % 100);
                    WS_GIV = (short) ((TDCCDS.REDEFINES6.BEG_DATE_YY - WS_REMB) / 100);
                    if (WS_REMA == 0 
                        && WS_REMB != 0) {
                        WS_LEAP = 1;
                    }
                }
                if (WS_LEAP == 0) {
                    TDCCDS.REDEFINES6.BEG_DATE_DD = 30;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.REDEFINES6);
                    TDCCDS.BEG_DATE = Integer.parseInt(JIBS_tmp_str[0]);
                }
            } else if (TDCCDS.REDEFINES6.BEG_DATE_DD == 29) {
                TDCCDS.REDEFINES6.BEG_DATE_DD = 30;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.REDEFINES6);
                TDCCDS.BEG_DATE = Integer.parseInt(JIBS_tmp_str[0]);
            } else {
            }
        }
    }
    public void R000_GET_END_DD() throws IOException,SQLException,Exception {
        if (TDCCDS.REDEFINES11.END_DATE_MM == 2 
            && TDCCDS.OPT != 'A' 
            && TDCCDS.OPT != 'B' 
            && TDCCDS.REDEFINES11.END_DATE_DD < TDCCDS.REDEFINES6.BEG_DATE_DD) {
            if (TDCCDS.REDEFINES11.END_DATE_DD == 28) {
                WS_REMA = (short) (TDCCDS.REDEFINES11.END_DATE_YY % 400);
                WS_GIV = (short) ((TDCCDS.REDEFINES11.END_DATE_YY - WS_REMA) / 400);
                if (WS_REMA == 0) {
                    WS_LEAP = 1;
                } else {
                    WS_REMA = (short) (TDCCDS.REDEFINES11.END_DATE_YY % 4);
                    WS_GIV = (short) ((TDCCDS.REDEFINES11.END_DATE_YY - WS_REMA) / 4);
                    WS_REMB = (short) (TDCCDS.REDEFINES11.END_DATE_YY % 100);
                    WS_GIV = (short) ((TDCCDS.REDEFINES11.END_DATE_YY - WS_REMB) / 100);
                    if (WS_REMA == 0 
                        && WS_REMB != 0) {
                        WS_LEAP = 1;
                    }
                }
                if (WS_LEAP == 0) {
                    TDCCDS.REDEFINES6.BEG_DATE_DD = TDCCDS.REDEFINES11.END_DATE_DD;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.REDEFINES6);
                    TDCCDS.BEG_DATE = Integer.parseInt(JIBS_tmp_str[0]);
                }
            } else if (TDCCDS.REDEFINES11.END_DATE_DD == 29) {
                TDCCDS.REDEFINES6.BEG_DATE_DD = TDCCDS.REDEFINES11.END_DATE_DD;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCCDS.REDEFINES6);
                TDCCDS.BEG_DATE = Integer.parseInt(JIBS_tmp_str[0]);
            } else {
            }
        }
    }
    public void R000_GET_CDS_MONS() throws IOException,SQLException,Exception {
        TDCCDS.MONS = (short) (( TDCCDS.REDEFINES11.END_DATE_YY - TDCCDS.REDEFINES6.BEG_DATE_YY ) * 12 + ( TDCCDS.REDEFINES11.END_DATE_MM - TDCCDS.REDEFINES6.BEG_DATE_MM ));
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCCDS.RC_MSG.RC != 0) {
            CEP.TRC(SCCGWA, "TDCCDS=");
            CEP.TRC(SCCGWA, TDCCDS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
