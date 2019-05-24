package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPRFEHIS;
import com.hisun.BP.BPRFHIS;
import com.hisun.BP.BPRFHIST;
import com.hisun.BP.BPRTHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRJRN;

public class CMZQFHIS {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CLEAR_DATE = 0;
    CMZQFHIS_WS_FEE_INFO WS_FEE_INFO = new CMZQFHIS_WS_FEE_INFO();
    char WS_EOF_FHIS_FLG = ' ';
    char WS_EOF_FHIST_FLG = ' ';
    char WS_EOF_HMIB_FLG = ' ';
    char WS_EOF_FEHIS_FLG = ' ';
    char WS_FETCH_FIRST_FLG = ' ';
    char WS_EOF_THIS_FLG = ' ';
    char WS_DETAIL_FND_FLG = ' ';
    char WS_SCTJRN_FND_FLG = ' ';
    char WS_FIRST_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCRJRNH SCRJRNH = new SCRJRNH();
    SCRJRN SCRJRN = new SCRJRN();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    AIRHMIB AIRHMIB = new AIRHMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CMCQFHIS CMCQFHIS;
    public void MP(SCCGWA SCCGWA, CMCQFHIS CMCQFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCQFHIS = CMCQFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZQFHIS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        WS_I = 0;
        CMCQFHIS.CNT = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INP_PROC();
        if (pgmRtn) return;
        WS_I = 0;
        CEP.TRC(SCCGWA, "STARTBR BPTFHIS1/2");
        if (CMCQFHIS.INQ_CTL == null) CMCQFHIS.INQ_CTL = "";
        JIBS_tmp_int = CMCQFHIS.INQ_CTL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCQFHIS.INQ_CTL += " ";
        if ((CMCQFHIS.INQ_REF.trim().length() > 0 
            && CMCQFHIS.INQ_REF.charAt(0) != 0X00) 
            || (CMCQFHIS.INQ_CTL.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("2"))) {
            B020_CHK_REF_PROC();
            if (pgmRtn) return;
        }
        if (CMCQFHIS.INQ_CTL == null) CMCQFHIS.INQ_CTL = "";
        JIBS_tmp_int = CMCQFHIS.INQ_CTL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCQFHIS.INQ_CTL += " ";
        if (CMCQFHIS.INQ_CTL.substring(0, 1).equalsIgnoreCase("1")) {
            B030_BROWSE_FHIS_PROC();
            if (pgmRtn) return;
        }
        if (CMCQFHIS.INQ_CTL == null) CMCQFHIS.INQ_CTL = "";
        JIBS_tmp_int = CMCQFHIS.INQ_CTL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCQFHIS.INQ_CTL += " ";
        if (CMCQFHIS.INQ_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            B050_BROWSE_HMIB_PROC();
            if (pgmRtn) return;
        }
        if (CMCQFHIS.INQ_CTL == null) CMCQFHIS.INQ_CTL = "";
        JIBS_tmp_int = CMCQFHIS.INQ_CTL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCQFHIS.INQ_CTL += " ";
        if (CMCQFHIS.INQ_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("2")) {
            WS_FETCH_FIRST_FLG = '1';
            B050_BROWSE_HMIB_PROC();
            if (pgmRtn) return;
        }
        if (CMCQFHIS.INQ_CTL == null) CMCQFHIS.INQ_CTL = "";
        JIBS_tmp_int = CMCQFHIS.INQ_CTL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCQFHIS.INQ_CTL += " ";
        if (CMCQFHIS.INQ_CTL.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && (CMCQFHIS.DC_FLG != 'D')) {
            B070_BROWSE_FEEHIS_PROC();
            if (pgmRtn) return;
        }
        if (CMCQFHIS.INQ_CTL == null) CMCQFHIS.INQ_CTL = "";
        JIBS_tmp_int = CMCQFHIS.INQ_CTL.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCQFHIS.INQ_CTL += " ";
        if (CMCQFHIS.INQ_CTL.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            B080_BROWSE_THIS_PROC();
            if (pgmRtn) return;
        }
        CMCQFHIS.CNT += WS_I;
        CMCQFHIS.CLEAR_DATE = WS_CLEAR_DATE;
    }
    public void B010_CHK_INP_PROC() throws IOException,SQLException,Exception {
        if (CMCQFHIS.AC_DT == 0 
            || !IBS.isNumeric(CMCQFHIS.AC_DT+"") 
            || CMCQFHIS.JRNNO == 0 
            || !IBS.isNumeric(CMCQFHIS.JRNNO+"")) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INP_DT_JRN_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CMCQFHIS.AC_DT);
            CEP.TRC(SCCGWA, CMCQFHIS.JRNNO);
        }
    }
    public void B020_CHK_REF_PROC() throws IOException,SQLException,Exception {
        WS_DETAIL_FND_FLG = 'N';
        IBS.init(SCCGWA, SCRJRN);
        IBS.init(SCCGWA, SCRJRNH);
        SCRJRN.AC_DATE = CMCQFHIS.AC_DT;
        SCRJRN.KEY.JRN_NO = CMCQFHIS.JRNNO;
        SCRJRN.AP_REF = CMCQFHIS.INQ_REF;
        T000_READ_SCTJRN1_REF();
        if (pgmRtn) return;
        if (WS_SCTJRN_FND_FLG == 'N') {
            WS_DETAIL_FND_FLG = 'N';
        } else {
            WS_DETAIL_FND_FLG = 'Y';
            WS_CLEAR_DATE = SCRJRN.CLEAR_DATE;
        }
        if (WS_DETAIL_FND_FLG == 'N') {
            IBS.init(SCCGWA, SCRJRN);
            SCRJRN.AC_DATE = CMCQFHIS.AC_DT;
            SCRJRN.KEY.JRN_NO = CMCQFHIS.JRNNO;
            SCRJRN.AP_REF = CMCQFHIS.INQ_REF;
            T000_READ_SCTJRN2_REF();
            if (pgmRtn) return;
            if (WS_SCTJRN_FND_FLG == 'N') {
                WS_DETAIL_FND_FLG = 'N';
            } else {
                WS_DETAIL_FND_FLG = 'Y';
                WS_CLEAR_DATE = SCRJRN.CLEAR_DATE;
            }
        }
        if (WS_DETAIL_FND_FLG == 'N') {
            IBS.init(SCCGWA, SCRJRNH);
            SCRJRNH.AC_DATE = CMCQFHIS.AC_DT;
            SCRJRNH.KEY.JRN_NO = CMCQFHIS.JRNNO;
            SCRJRNH.AP_REF = CMCQFHIS.INQ_REF;
            T000_READ_SCTJRNH_REF();
            if (pgmRtn) return;
            if (WS_SCTJRN_FND_FLG == 'N') {
                WS_DETAIL_FND_FLG = 'N';
            } else {
                WS_DETAIL_FND_FLG = 'Y';
                WS_CLEAR_DATE = SCRJRNH.CLEAR_DATE;
            }
        }
        CEP.TRC(SCCGWA, CMCQFHIS.INQ_CTL);
        if (WS_DETAIL_FND_FLG == 'N') {
            CMCQFHIS.INQ_CTL = "ZEROZEROZEROZEROZERO";
            CMCQFHIS.INQ_RTN.INQ_JRN_FLG = 'N';
        }
        CEP.TRC(SCCGWA, CMCQFHIS.INQ_RTN.INQ_JRN_FLG);
    }
    public void B030_BROWSE_FHIS_PROC() throws IOException,SQLException,Exception {
        WS_DETAIL_FND_FLG = 'N';
        T000_STARTBR_BPTFHIS1();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIS1();
        if (pgmRtn) return;
        while (WS_EOF_FHIS_FLG != 'Y') {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
            WS_DETAIL_FND_FLG = 'Y';
            WS_I += 1;
            if (BPRFHIS.TX_TOOL.trim().length() == 0) {
                CMCQFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.KEY.AC;
                CMCQFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            } else {
                CMCQFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.TX_TOOL;
                CMCQFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            }
            CMCQFHIS.INFO[WS_I-1].AC_TYP = "W";
            CMCQFHIS.INFO[WS_I-1].JRN_SEQ = BPRFHIS.KEY.JRN_SEQ;
            CMCQFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIS.DRCRFLG;
            CMCQFHIS.INFO[WS_I-1].TX_CCY = BPRFHIS.TX_CCY;
            CMCQFHIS.INFO[WS_I-1].TX_CCY_TYP = "" + BPRFHIS.TX_CCY_TYPE;
            JIBS_tmp_int = CMCQFHIS.INFO[WS_I-1].TX_CCY_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CMCQFHIS.INFO[WS_I-1].TX_CCY_TYP = "0" + CMCQFHIS.INFO[WS_I-1].TX_CCY_TYP;
            CMCQFHIS.INFO[WS_I-1].TX_AMT = BPRFHIS.TX_AMT;
            CMCQFHIS.INFO[WS_I-1].OPP_AC = BPRFHIS.RLT_AC;
