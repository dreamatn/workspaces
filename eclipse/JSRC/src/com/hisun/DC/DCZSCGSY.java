package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.CI.CICSAGEN;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCCHDC;

public class DCZSCGSY {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC929";
    String K_HIS_REMARK = "                            ";
    String K_HIS_COPYBOOK = "DCRCTCDC";
    String K_TBL_CTCDC = "DCTCTCDC";
    String K_TBL_CDDAT = "DCTCDDAT";
    String K_TBL_CDORD = "DCTCDORD";
    String WS_ERR_MSG = " ";
    String WS_CI_NO = " ";
    char WS_OLD_PROD_LMT_FLG = ' ';
    String WS_OLD_CARD_TYP = " ";
    char WS_OLD_SAME_TFR_FLG = ' ';
    char WS_OLD_DIFF_TFR_FLG = ' ';
    char WS_OLD_DRAW_OVER_FLG = ' ';
    char WS_OLD_DB_FREE_FLG = ' ';
    String WS_BR_NM = " ";
    int WS_OWN_BR = 0;
    int WS_OWN_DT = 0;
    String WS_OWN_TLR = " ";
    String WS_OWN_REASON = " ";
    char WS_OLD_CARD_STS = ' ';
    DCZSCGSY_WS_OLD_CARD_CRDLT_INFO WS_OLD_CARD_CRDLT_INFO = new DCZSCGSY_WS_OLD_CARD_CRDLT_INFO();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    DCCHDAMC DCCHDAMC = new DCCHDAMC();
    DCCHDAMC DCCODAMC = new DCCHDAMC();
    DCCHDAMC DCCNDAMC = new DCCHDAMC();
    DCRCITCD DCRCITCD = new DCRCITCD();
    CICCHDC CICCHDC = new CICCHDC();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    TDCCHDC TDCCHDC = new TDCCHDC();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DCCF290 DCCF290 = new DCCF290();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9290 DCCS9290;
    public void MP(SCCGWA SCCGWA, DCCS9290 DCCS9290) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9290 = DCCS9290;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCGSY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GO");
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B115_CHECK_CARD_STS();
        if (pgmRtn) return;
        B120_CHECK_DCTCTCDC();
        if (pgmRtn) return;
        B125_UPDATE_DCRCITCD();
        if (pgmRtn) return;
        if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
            || DCCS9290.FETCH_TYP.equalsIgnoreCase("03") 
            || DCCS9290.FETCH_TYP.equalsIgnoreCase("04")) {
            B130_CHANGE_CARD_PROCESS();
            if (pgmRtn) return;
            B145_CHANGE_CARD_LIMIT();
            if (pgmRtn) return;
            B146_CHANGE_APPLEPAY();
            if (pgmRtn) return;
            B150_OLD_CARD_CANCEL();
            if (pgmRtn) return;
            B155_UPDATE_DDTCCY();
            if (pgmRtn) return;
            B160_UPDATE_TDTSMST();
            if (pgmRtn) return;
            if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02")) {
                B170_UPDATE_BPLOST();
                if (pgmRtn) return;
            }
        }
        if (DCCS9290.AGENT_FLG == 'Y') {
            B175_REGIST_AGENT_INFO();
            if (pgmRtn) return;
        }
        B180_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B200_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9290.FETCH_TYP);
        CEP.TRC(SCCGWA, DCCS9290.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9290.CARD_NO);
        CEP.TRC(SCCGWA, DCCS9290.CI_NM);
        CEP.TRC(SCCGWA, DCCS9290.ID_NO);
        CEP.TRC(SCCGWA, DCCS9290.ID_TYP);
        CEP.TRC(SCCGWA, DCCS9290.E_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9290.OLD_E_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9290.SOCIAL_NO);
        CEP.TRC(SCCGWA, DCCS9290.SOCIAL_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9290.OLD_SOCIAL_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9290.AGENT_FLG);
        CEP.TRC(SCCGWA, DCCS9290.AGENT_CI_NAME);
        CEP.TRC(SCCGWA, DCCS9290.AGENT_ID_TYP);
        CEP.TRC(SCCGWA, DCCS9290.AGENT_ID_NO);
        CEP.TRC(SCCGWA, DCCS9290.AGENT_PHONE);
        if (DCCS9290.FETCH_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_TXN_TYPE_ERR);
        }
        if (DCCS9290.OLD_CARD_NO.trim().length() == 0 
            && DCCS9290.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCS9290.CI_NM.trim().length() == 0 
            || DCCS9290.ID_NO.trim().length() == 0 
            || DCCS9290.ID_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME);
        }
        if (DCCS9290.CARD_NO.equalsIgnoreCase(DCCS9290.OLD_CARD_NO)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_NEW_OLD_CARD_SAME);
        }
        if (DCCS9290.OLD_CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCS9290.OLD_CARD_NO;
            T000_READUPD_DCTCDDAT();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_OLD_CARD_STS = DCRCDDAT.CARD_STS;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
                && !DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS);
            }
            WS_OLD_PROD_LMT_FLG = DCRCDDAT.PROD_LMT_FLG;
            WS_OLD_SAME_TFR_FLG = DCRCDDAT.SAME_NAME_TFR_FLG;
            WS_OLD_DIFF_TFR_FLG = DCRCDDAT.DIFF_NAME_TFR_FLG;
            WS_OLD_DRAW_OVER_FLG = DCRCDDAT.DRAW_OVER_FLG;
            WS_OLD_DB_FREE_FLG = DCRCDDAT.DOUBLE_FREE_FLG;
            WS_OLD_CARD_TYP = DCRCDDAT.CARD_TYP;
            DCRCDDAT.CARD_STS = 'C';
            DCRCDDAT.CLO_DT = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        }
    }
    public void B115_CHECK_CARD_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS9290.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        if (!DCCS9290.FETCH_TYP.equalsIgnoreCase("05")) {
            if (DCRCDDAT.CARD_STS != '3') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_RECEIPTED);
            }
            if (DCCS9290.FETCH_TYP.equalsIgnoreCase("01") 
                || DCCS9290.FETCH_TYP.equalsIgnoreCase("06") 
                || DCCS9290.FETCH_TYP.equalsIgnoreCase("03") 
                || DCCS9290.FETCH_TYP.equalsIgnoreCase("04")) {
                DCRCDDAT.CARD_STS = '2';
            } else {
                DCRCDDAT.CARD_STS = 'N';
            }
            if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
                || DCCS9290.FETCH_TYP.equalsIgnoreCase("03") 
                || DCCS9290.FETCH_TYP.equalsIgnoreCase("04")) {
                DCRCDDAT.CARD_TYP = WS_OLD_CARD_TYP;
                DCRCDDAT.PROD_LMT_FLG = WS_OLD_PROD_LMT_FLG;
                DCRCDDAT.SAME_NAME_TFR_FLG = WS_OLD_SAME_TFR_FLG;
                DCRCDDAT.DIFF_NAME_TFR_FLG = WS_OLD_DIFF_TFR_FLG;
                DCRCDDAT.DRAW_OVER_FLG = WS_OLD_DRAW_OVER_FLG;
                DCRCDDAT.DOUBLE_FREE_FLG = WS_OLD_DB_FREE_FLG;
            }
        } else {
            if (DCRCDDAT.CARD_STS != '2') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
            }
            DCRCDDAT.CARD_STS = '3';
        }
        if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
            && WS_OLD_CARD_STS == '2') {
            DCRCDDAT.CARD_STS = '2';
        }
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCS9290.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        if (!DCCS9290.FETCH_TYP.equalsIgnoreCase("05")) {
            if (DCRCDORD.CRT_STS != '1') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE);
            }
            DCRCDORD.CRT_STS = '3';
        } else {
            if (DCRCDORD.CRT_STS != '3') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS);
            }
            DCRCDORD.CRT_STS = '1';
        }
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
    }
    public void B120_CHECK_DCTCTCDC() throws IOException,SQLException,Exception {
        if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
            || DCCS9290.FETCH_TYP.equalsIgnoreCase("03") 
            || DCCS9290.FETCH_TYP.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.KEY.OLD_CARD_NO = DCCS9290.OLD_CARD_NO;
            T000_READUPD_DCTCTCDC();
            if (pgmRtn) return;
            if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
                && DCRCTCDC.CHG_STS != '2') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_BE_IN_BANK);
            }
            DCRCTCDC.CHG_STS = '3';
            DCRCTCDC.FET_DT = SCCGWA.COMM_AREA.AC_DATE;
            DCRCTCDC.FET_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRCTCDC.FET_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCTCDC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCTCDC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTCTCDC();
            if (pgmRtn) return;
        }
    }
    public void B125_UPDATE_DCRCITCD() throws IOException,SQLException,Exception {
        if (DCCS9290.FETCH_TYP.equalsIgnoreCase("02") 
            || DCCS9290.FETCH_TYP.equalsIgnoreCase("03") 
            || DCCS9290.FETCH_TYP.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = DCCS9290.OLD_CARD_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCRCITCD.STS);
            if (DCRCITCD.STS == '1') {
                WS_BR_NM = DCRCITCD.BR_NM;
                WS_OWN_BR = DCRCITCD.OWN_BR;
                WS_OWN_DT = DCRCITCD.OWN_DT;
                WS_OWN_TLR = DCRCITCD.OWN_TLR;
                WS_OWN_REASON = DCRCITCD.RMK;
                IBS.init(SCCGWA, DCRCITCD);
                DCRCITCD.KEY.CARD_NO = DCCS9290.CARD_NO;
                T000_READUPD_DCTCITCD();
                if (pgmRtn) return;
                DCRCITCD.STS = '1';
                DCRCITCD.BR_NM = WS_BR_NM;
                DCRCITCD.OWN_BR = WS_OWN_BR;
                DCRCITCD.OWN_DT = WS_OWN_DT;
                DCRCITCD.OWN_TLR = WS_OWN_TLR;
                DCRCITCD.RMK = WS_OWN_REASON;
                T000_UPDATE_DCTCITCD();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DCRCITCD);
        DCRCITCD.KEY.CARD_NO = DCCS9290.CARD_NO;
        T000_READUPD_DCTCITCD();
        if (pgmRtn) return;
        DCRCITCD.THD_FET_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCITCD();
        if (pgmRtn) return;
    }
    public void B130_CHANGE_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCHDC);
        CICCHDC.FUNC = 'C';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_OLD = DCCS9290.OLD_CARD_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_OLD = '2';
        CICCHDC.DATA_FOR_CHANGE.ENTY_NO_NEW = DCCS9290.CARD_NO;
        CICCHDC.DATA_FOR_CHANGE.ENTY_TYP_NEW = '2';
        CICCHDC.DATA_FOR_CHANGE.IC_AID_FLG = 'N';
        S000_CALL_CIZCHDC();
        if (pgmRtn) return;
    }
    public void B145_CHANGE_CARD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.CARD_NO = DCCS9290.OLD_CARD_NO;
        T000_STARTBR_DCTCRDLT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCRDLT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B150_MOVE_OLD_DCTCRDLT();
            if (pgmRtn) return;
            B160_INSERT_NEW_DCTCRDLT();
            if (pgmRtn) return;
            DCRCRDLT.KEY.CARD_NO = DCCS9290.CARD_NO;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCRDLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCRDLT();
        if (pgmRtn) return;
    }
    public void B146_CHANGE_APPLEPAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRAPPLC);
        DCRAPPLC.SPAN = DCCS9290.OLD_CARD_NO;
        T000_STARTBR_DCTAPPLC();
        if (pgmRtn) return;
        T000_READNEXT_DCTAPPLC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            DCRAPPLC.SPAN = DCCS9290.CARD_NO;
            DCRAPPLC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRAPPLC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTAPPLC();
            if (pgmRtn) return;
            T000_READNEXT_DCTAPPLC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTAPPLC();
        if (pgmRtn) return;
    }
    public void B150_OLD_CARD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.OLD_AC = DCCS9290.OLD_CARD_NO;
        BPCSOCAC.AC = DCCS9290.CARD_NO;
        BPCSOCAC.STS = 'K';
        BPCSOCAC.IC_AID_FLG = 'N';
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B155_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'U';
        DDCIMCYY.DATA.AC_OLD = DCCS9290.OLD_CARD_NO;
        DDCIMCYY.DATA.AC_NEW = DCCS9290.CARD_NO;
        S000_CALL_DDZIMCYY();
        if (pgmRtn) return;
    }
    public void B160_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCCHDC);
        TDCCHDC.OLD_AC = DCCS9290.OLD_CARD_NO;
        TDCCHDC.NEW_AC = DCCS9290.CARD_NO;
        S000_CALL_TDZCHDC();
        if (pgmRtn) return;
    }
    public void B170_UPDATE_BPLOST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLOSS);
        BPCPLOSS.DATA_INFO.AC = DCCS9290.OLD_CARD_NO;
        BPCPLOSS.DATA_INFO.STS = '1';
        BPCPLOSS.INFO.FUNC = 'I';
        BPCPLOSS.INFO.INDEX_FLG = "2";
        S000_CALL_BPZPLOSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_NO);
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.LOS_NO = BPCPLOSS.DATA_INFO.LOS_NO;
        CEP.TRC(SCCGWA, "LOSS-AC");
        BPCSLOSS.FUNC = 'U';
        CEP.TRC(SCCGWA, "N-CANCEL-999");
        BPCSLOSS.STS = '5';
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCSLOSS.NEW_BV_NO = DCCS9290.CARD_NO;
        S000_CALL_BPZSLOSS();
        if (pgmRtn) return;
    }
    public void B175_REGIST_AGENT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = WS_CI_NO;
        CICSAGEN.OUT_AC = DCCS9290.CARD_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.ID_TYP = DCCS9290.AGENT_ID_TYP;
        CICSAGEN.ID_NO = DCCS9290.AGENT_ID_NO;
        CICSAGEN.CI_NAME = DCCS9290.AGENT_CI_NAME;
        CICSAGEN.PHONE = DCCS9290.AGENT_PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B180_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        DCCF290.CARD_NO = DCCS9290.CARD_NO;
        DCCF290.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCF290.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF290;
        SCCFMT.DATA_LEN = 33;
        CEP.TRC(SCCGWA, DCCF290);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B150_MOVE_OLD_DCTCRDLT() throws IOException,SQLException,Exception {
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_REGN_TYP = DCRCRDLT.KEY.REGN_TYP;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_CHNL_NO = DCRCRDLT.KEY.CHNL_NO;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_TYPE = DCRCRDLT.KEY.TXN_TYPE;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_LMT_CCY = DCRCRDLT.KEY.LMT_CCY;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_DT = DCRCRDLT.STA_DT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_TM = DCRCRDLT.STA_TM;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_DT = DCRCRDLT.END_DT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_TM = DCRCRDLT.END_TM;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_LMT_AMT = DCRCRDLT.TXN_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_AMT = DCRCRDLT.DLY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_VOL = DCRCRDLT.DLY_LMT_VOL;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_AMT = DCRCRDLT.MLY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_VOL = DCRCRDLT.MLY_LMT_VOL;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SYY_LMT_AMT = DCRCRDLT.SYY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_YLY_LMT_AMT = DCRCRDLT.YLY_LMT_AMT;
        WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SE_LMT_AMT = DCRCRDLT.SE_LMT_AMT;
    }
    public void B160_INSERT_NEW_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCRDLT);
        DCRCRDLT.KEY.REGN_TYP = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_REGN_TYP;
        DCRCRDLT.KEY.CHNL_NO = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_CHNL_NO;
        DCRCRDLT.KEY.TXN_TYPE = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_TYPE;
        DCRCRDLT.KEY.LMT_CCY = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_LMT_CCY;
        DCRCRDLT.STA_DT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_DT;
        DCRCRDLT.STA_TM = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_STA_TM;
        DCRCRDLT.END_DT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_DT;
        DCRCRDLT.END_TM = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_END_TM;
        DCRCRDLT.TXN_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_TXN_LMT_AMT;
        DCRCRDLT.DLY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_AMT;
        DCRCRDLT.DLY_LMT_VOL = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_DLY_LMT_VOL;
        DCRCRDLT.MLY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_AMT;
        DCRCRDLT.MLY_LMT_VOL = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_MLY_LMT_VOL;
        DCRCRDLT.SYY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SYY_LMT_AMT;
        DCRCRDLT.YLY_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_YLY_LMT_AMT;
        DCRCRDLT.SE_LMT_AMT = WS_OLD_CARD_CRDLT_INFO.WS_CRDLT_SE_LMT_AMT;
    }
    public void B200_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHDAMC);
        IBS.init(SCCGWA, DCCNDAMC);
        DCCNDAMC.OLD_CARD_NO = DCCS9290.OLD_CARD_NO;
        DCCNDAMC.HOLDER_IDTYP = DCCS9290.ID_TYP;
        DCCNDAMC.HOLDER_IDNO = DCCS9290.ID_NO;
        DCCNDAMC.HOLDER_NAME = DCCS9290.CI_NM;
        DCCNDAMC.NEW_CARD_NO = DCCS9290.CARD_NO;
        DCCNDAMC.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNDAMC.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "DAMAGE/OUT CARD DIRECT CHANGE";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHDAMC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS9290.CARD_NO;
        BPCPNHIS.INFO.CI_NO = WS_CI_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 416;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCODAMC;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNDAMC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        if (BPCPLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHANGE-DC ", CICCHDC);
        CEP.TRC(SCCGWA, CICCHDC.RC);
        if (CICCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        CEP.TRC(SCCGWA, BPCSOCAC.RC.RC_CODE);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_TDZCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-Z-CHAG-DC", TDCCHDC);
        CEP.TRC(SCCGWA, CICCHDC.RC);
        if (CICCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCRDLT() throws IOException,SQLException,Exception {
