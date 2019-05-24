package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZCGHIS {
    boolean pgmRtn = false;
    short WS_DATA_LEN = 0;
    double WS_AMT = 0;
    String WS_KEY_DATA = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRCHIS CIRCHIS = new CIRCHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCGHIS CICCGHIS;
    CIRBAS CIRBASO;
    CIRBAS CIRBASN;
    CIRPDM CIRPDMO;
    CIRPDM CIRPDMN;
    CIRCDM CIRCDMO;
    CIRCDM CIRCDMN;
    CIRFDM CIRFDMO;
    CIRFDM CIRFDMN;
    CIRID CIRIDO;
    CIRID CIRIDN;
    CIRNAM CIRNAMO;
    CIRNAM CIRNAMN;
    CIRCNT CIRCNTO;
    CIRCNT CIRCNTN;
    CIRADR CIRADRO;
    CIRADR CIRADRN;
    public void MP(SCCGWA SCCGWA, CICCGHIS CICCGHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCGHIS = CICCGHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCGHIS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCGHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_WRITE_MOD_HIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_WRITE_MOD_HIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCGHIS.DATA.TABLE_NM);
        if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITBAS")) {
            B021_WRITE_CITBAS_MOD_HIS();
            if (pgmRtn) return;
        } else if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITPDM")) {
            B022_WRITE_CITPDM_MOD_HIS();
            if (pgmRtn) return;
        } else if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITCDM")) {
            B023_WRITE_CITCDM_MOD_HIS();
            if (pgmRtn) return;
        } else if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITFDM")) {
            B024_WRITE_CITFDM_MOD_HIS();
            if (pgmRtn) return;
        } else if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITID")) {
            B025_WRITE_CITID_MOD_HIS();
            if (pgmRtn) return;
        } else if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITADR")) {
            B026_WRITE_CITADR_MOD_HIS();
            if (pgmRtn) return;
        } else if (CICCGHIS.DATA.TABLE_NM.equalsIgnoreCase("CITCNT")) {
            B027_WRITE_CITCNT_MOD_HIS();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B021_WRITE_CITBAS_MOD_HIS() throws IOException,SQLException,Exception {
        CIRBASO = (CIRBAS) CICCGHIS.DATA.OLD_DATA_PTR;
        CIRBASN = (CIRBAS) CICCGHIS.DATA.NEW_DATA_PTR;
        if (!CIRBASO.SVR_LVL.equalsIgnoreCase(CIRBASN.SVR_LVL)) {
            IBS.init(SCCGWA, CIRCHIS);
            CIRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CIRCHIS.KEY.TABLE_NM = "CITBAS";
            CIRCHIS.KEY.KEY_DATA = CIRBASO.KEY.CI_NO;
            CHIS_KEY_KEY_DATA_LEN = CIRCHIS.KEY.KEY_DATA.length();
            CIRCHIS.KEY.FLD_NM = "SVR_LVL";
            CIRCHIS.OLD_DATA = CIRBASO.SVR_LVL;
            CHIS_OLD_DATA_LEN = CIRCHIS.OLD_DATA.length();
            CIRCHIS.NEW_DATA = CIRBASN.SVR_LVL;
            CHIS_NEW_DATA_LEN = CIRCHIS.NEW_DATA.length();
            CIRCHIS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRCHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRCHIS.SUP_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            CIRCHIS.SUP_TLR2 = SCCGWA.COMM_AREA.SUP2_ID;
            T000_WRITE_CITCHIS();
            if (pgmRtn) return;
        }
        if (CIRBASO.ORGIN_TP != CIRBASN.ORGIN_TP) {
            IBS.init(SCCGWA, CIRCHIS);
            CIRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CIRCHIS.KEY.TABLE_NM = "CITBAS";
            CIRCHIS.KEY.KEY_DATA = CIRBASO.KEY.CI_NO;
            CHIS_KEY_KEY_DATA_LEN = CIRCHIS.KEY.KEY_DATA.length();
            CIRCHIS.KEY.FLD_NM = "ORGIN_TP";
            CIRCHIS.OLD_DATA = "" + CIRBASO.ORGIN_TP;
            JIBS_tmp_int = CIRCHIS.OLD_DATA.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CIRCHIS.OLD_DATA = "0" + CIRCHIS.OLD_DATA;
            CHIS_OLD_DATA_LEN = CIRCHIS.OLD_DATA.length();
            CIRCHIS.NEW_DATA = "" + CIRBASN.ORGIN_TP;
            JIBS_tmp_int = CIRCHIS.NEW_DATA.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CIRCHIS.NEW_DATA = "0" + CIRCHIS.NEW_DATA;
            CHIS_NEW_DATA_LEN = CIRCHIS.NEW_DATA.length();
            CIRCHIS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRCHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRCHIS.SUP_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            CIRCHIS.SUP_TLR2 = SCCGWA.COMM_AREA.SUP2_ID;
            T000_WRITE_CITCHIS();
            if (pgmRtn) return;
        }
        if (!CIRBASO.ORIGIN.equalsIgnoreCase(CIRBASN.ORIGIN)) {
            IBS.init(SCCGWA, CIRCHIS);
            CIRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CIRCHIS.KEY.TABLE_NM = "CITBAS";
            CIRCHIS.KEY.KEY_DATA = CIRBASO.KEY.CI_NO;
            CHIS_KEY_KEY_DATA_LEN = CIRCHIS.KEY.KEY_DATA.length();
            CIRCHIS.KEY.FLD_NM = "ORIGIN";
            CIRCHIS.OLD_DATA = CIRBASO.ORIGIN;
            CHIS_OLD_DATA_LEN = CIRCHIS.OLD_DATA.length();
            CIRCHIS.NEW_DATA = CIRBASN.ORIGIN;
            CHIS_NEW_DATA_LEN = CIRCHIS.NEW_DATA.length();
            CIRCHIS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRCHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRCHIS.SUP_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            CIRCHIS.SUP_TLR2 = SCCGWA.COMM_AREA.SUP2_ID;
            T000_WRITE_CITCHIS();
            if (pgmRtn) return;
        }
        if (!CIRBASO.ORIGIN2.equalsIgnoreCase(CIRBASN.ORIGIN2)) {
            IBS.init(SCCGWA, CIRCHIS);
            CIRCHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            CIRCHIS.KEY.TABLE_NM = "CITBAS";
            CIRCHIS.KEY.KEY_DATA = CIRBASO.KEY.CI_NO;
            CHIS_KEY_KEY_DATA_LEN = CIRCHIS.KEY.KEY_DATA.length();
            CIRCHIS.KEY.FLD_NM = "ORIGIN2";
            CIRCHIS.OLD_DATA = CIRBASO.ORIGIN2;
            CHIS_OLD_DATA_LEN = CIRCHIS.OLD_DATA.length();
            CIRCHIS.NEW_DATA = CIRBASN.ORIGIN2;
            CHIS_NEW_DATA_LEN = CIRCHIS.NEW_DATA.length();
            CIRCHIS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRCHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRCHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRCHIS.SUP_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            CIRCHIS.SUP_TLR2 = SCCGWA.COMM_AREA.SUP2_ID;
            T000_WRITE_CITCHIS();
            if (pgmRtn) return;
        }
