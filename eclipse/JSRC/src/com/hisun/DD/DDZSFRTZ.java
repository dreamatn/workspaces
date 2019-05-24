package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSFRTZ {
    int JIBS_tmp_int;
    DBParm DDTADMN_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DDA02";
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL = "5122";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_HIS_COPYBOOK_NAME = "DDCS8820";
    String K_HIS_REMARKS = "ACTIVATION ACCOUNT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_FRTZ_STRDT = 0;
    int WS_FRTZ_EXPDT = 0;
    double WS_CURR_BAL = 0;
    char WS_ADMN_FLG = ' ';
    char WS_CHG_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    DDRADMN DDRADMN = new DDRADMN();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCFA02 DDCFA02 = new DDCFA02();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    CICMAGT CICMAGT = new CICMAGT();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSFRTZ DDCSFRTZ;
    public void MP(SCCGWA SCCGWA, DDCSFRTZ DDCSFRTZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSFRTZ = DDCSFRTZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSFRTZ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B030_READ_UPD_MST_REC();
        if (pgmRtn) return;
        B040_GET_CCY_REC();
        if (pgmRtn) return;
        B050_CHK_CCY_CURR_BAL();
        if (pgmRtn) return;
        B055_CHK_AC_STS();
        if (pgmRtn) return;
        if (DDCSFRTZ.FUNC == '1') {
            B060_GET_PRD_INF();
            if (pgmRtn) return;
            B035_GEN_CI_AGT_NO();
            if (pgmRtn) return;
            B060_ADD_ADMN_REC();
            if (pgmRtn) return;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 42 - 1) + "1" + DDRMST.AC_STS_WORD.substring(42 + 1 - 1);
            R000_REWRITE_DDTMST();
            if (pgmRtn) return;
            B070_INQ_GL_MASTER();
            if (pgmRtn) return;
            B080_WRITE_GL_MASTER_PROC();
            if (pgmRtn) return;
        } else if (DDCSFRTZ.FUNC == '2') {
            B060_UPD_ADMN_REC();
            if (pgmRtn) return;
        } else if (DDCSFRTZ.FUNC == '3') {
            B035_CAN_CI_AGT_NO();
            if (pgmRtn) return;
            B060_CAN_ADMN_REC();
            if (pgmRtn) return;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 42 - 1) + "0" + DDRMST.AC_STS_WORD.substring(42 + 1 - 1);
            R000_REWRITE_DDTMST();
            if (pgmRtn) return;
        } else if (DDCSFRTZ.FUNC == '4') {
            B060_STOP_ADMN_REC();
            if (pgmRtn) return;
            R000_REWRITE_DDTMST();
            if (pgmRtn) return;
        } else if (DDCSFRTZ.FUNC == '5') {
            B060_REUSE_ADMN_REC();
            if (pgmRtn) return;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 42 - 1) + "1" + DDRMST.AC_STS_WORD.substring(42 + 1 - 1);
            R000_REWRITE_DDTMST();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B070_OUTPUT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.JRN_NO != 0) {
            B170_NFIN_TX_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSFRTZ.FUNC == '1') {
            if (DDCSFRTZ.EXPDT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DT_MNOT_LT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (DDCSFRTZ.FUNC == '2') {
            if (DDCSFRTZ.EXPDT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DT_MNOT_LT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B170_NFIN_TX_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = DDCSFRTZ.CUS_AC;
        BPCPNHIS.INFO.CCY = DDCSFRTZ.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSFRTZ.CCY_TYP;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSFRTZ.CUS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B030_READ_UPD_MST_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSFRTZ.CUS_AC;
        T000_READ_UPD_DDTMST();
        if (pgmRtn) return;
    }
    public void B040_GET_CCY_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSFRTZ.CUS_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSFRTZ.CCY;
        CICQACAC.DATA.CR_FLG = DDCSFRTZ.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void B050_CHK_CCY_CURR_BAL() throws IOException,SQLException,Exception {
        if (DDRCCY.CURR_BAL < 0 
            && (DDCSFRTZ.FUNC == '1' 
            || DDCSFRTZ.FUNC == '2')) {
            WS_CURR_BAL = DDRCCY.CURR_BAL * ( -1 );
            if (DDCSFRTZ.BAL_AMT < WS_CURR_BAL) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LT_USED_BAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B055_CHK_AC_STS() throws IOException,SQLException,Exception {
    }
    public void B060_GET_PRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDVMRAT);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        if (DDVMPRD.VAL.OVERDRAFT_FAC == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PRD_OVR_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B035_GEN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS026";
        CICMAGT.DATA.AGT_STS = 'N';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSFRTZ.CUS_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        CEP.TRC(SCCGWA, CICMAGT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_STS);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMAGT.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICMAGT.DATA.AGT_LVL);
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B035_CAN_CI_AGT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'D';
        CICMAGT.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        CICMAGT.DATA.AGT_TYP = "IBS026";
        CICMAGT.DATA.AGT_STS = 'C';
        CICMAGT.DATA.ENTY_TYP = '1';
        CICMAGT.DATA.ENTY_NO = DDCSFRTZ.CUS_AC;
        CICMAGT.DATA.AGT_LVL = 'A';
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B060_ADD_ADMN_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDCSFRTZ.PROL_NO;
        T000_READ_DDTADMN();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'F') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROL_NO_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_UPD_DDTADMN_01();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'F') {
            DDRADMN.ADP_STS = 'F';
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTADMN();
            if (pgmRtn) return;
            B035_CAN_CI_AGT_NO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRADMN.ADP_STS = 'O';
        DDRADMN.KEY.ADP_TYPE = "8";
        R000_GENERAL_MOVE();
        if (pgmRtn) return;
        T000_WRITE_DDTADMN();
        if (pgmRtn) return;
    }
    public void R000_GENERAL_MOVE() throws IOException,SQLException,Exception {
        DDRADMN.ADP_STRDATE = DDCSFRTZ.STRDT;
        DDRADMN.ADP_EXPDATE = DDCSFRTZ.EXPDT;
        DDRADMN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRADMN.OD_AMT = DDCSFRTZ.BAL_AMT;
        DDRADMN.KEY.ADP_NO = DDCSFRTZ.PROL_NO;
    }
    public void R000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B060_UPD_ADMN_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSFRTZ.PROL_NO;
        DDRADMN.KEY.ADP_TYPE = "8";
        T000_READ_UPD_DDTADMN();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            B060_SET_CHG_FLG();
            if (pgmRtn) return;
            DDRADMN.OD_AMT = DDCSFRTZ.BAL_AMT;
            DDRADMN.ADP_EXPDATE = DDCSFRTZ.EXPDT;
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTADMN();
            if (pgmRtn) return;
        }
    }
    public void B060_CAN_ADMN_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSFRTZ.PROL_NO;
        DDRADMN.KEY.ADP_TYPE = "8";
        T000_READ_UPD_DDTADMN();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDRADMN.ADP_STS = 'F';
            DDRADMN.ADP_EXPDATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTADMN();
            if (pgmRtn) return;
        }
    }
    public void B060_STOP_ADMN_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSFRTZ.PROL_NO;
        DDRADMN.KEY.ADP_TYPE = "8";
        DDRADMN.ADP_STS = 'O';
        T000_READ_UPD_DDTADMN_02();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDRADMN.ADP_STS = 'S';
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTADMN();
            if (pgmRtn) return;
        }
    }
    public void B060_REUSE_ADMN_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSFRTZ.PROL_NO;
        DDRADMN.KEY.ADP_TYPE = "8";
        DDRADMN.ADP_STS = 'S';
        T000_READ_UPD_DDTADMN_02();
        if (pgmRtn) return;
        if (WS_ADMN_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDRADMN.ADP_STS = 'O';
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTADMN();
            if (pgmRtn) return;
        }
    }
    public void B060_SET_CHG_FLG() throws IOException,SQLException,Exception {
        WS_CHG_FLG = 'N';
        if (DDCSFRTZ.EXPDT != DDRADMN.ADP_EXPDATE) {
            WS_CHG_FLG = 'Y';
        }
        if (DDCSFRTZ.BAL_AMT != DDRADMN.OD_AMT) {
            WS_CHG_FLG = 'Y';
        }
        if (WS_CHG_FLG != 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DATA_NO_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCFA02);
        DDCFA02.FUNC = DDCSFRTZ.FUNC;
        DDCFA02.CUS_AC = DDCSFRTZ.CUS_AC;
        DDCFA02.CCY = DDCSFRTZ.CCY;
        DDCFA02.CCY_TYP = DDCSFRTZ.CCY_TYP;
        DDCFA02.PROL_NO = DDCSFRTZ.PROL_NO;
        DDCFA02.STRDT = DDCSFRTZ.STRDT;
        DDCFA02.EXPDT = DDCSFRTZ.EXPDT;
        DDCFA02.BAL_AMT = DDCSFRTZ.BAL_AMT;
        DDCFA02.AC = DDCSFRTZ.AC;
        DDCFA02.PROD_CD = DDCSFRTZ.PROD_CD;
        DDCFA02.BAL_AMT = DDCSFRTZ.BAL_AMT;
        DDCFA02.CNTR_TYP = DDCSFRTZ.CNTR_TYP;
        DDCFA02.CI_TYP = DDCSFRTZ.CI_TYP;
        DDCFA02.FIN_TYP = DDCSFRTZ.FIN_TYP;
        DDCFA02.AC_TYP = DDCSFRTZ.AC_TYP;
        DDCFA02.PROP_TYP = DDCSFRTZ.PROP_TYP;
        DDCFA02.TERM_CD = DDCSFRTZ.TERM_CD;
        DDCFA02.LOAN_TYP = DDCSFRTZ.LOAN_TYP;
        DDCFA02.WE_FLG = DDCSFRTZ.WE_FLG;
        DDCFA02.DT_FREE = DDCSFRTZ.DT_FREE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DDA02";
        SCCFMT.DATA_PTR = DDCFA02;
        SCCFMT.DATA_LEN = 156;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACLDD);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = DDCSFRTZ.CNTR_TYP;
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.BR);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.INPUT.OTH_PTR_LEN);
        BPCACLDD.PROD_CD = DDCSFRTZ.PROD_CD;
        BPCACLDD.CI_TYPE = DDCSFRTZ.CI_TYP;
        BPCACLDD.FIN_TYP = DDCSFRTZ.FIN_TYP;
        BPCACLDD.AC_TYP = DDCSFRTZ.AC_TYP;
        BPCACLDD.PROP_TYP = DDCSFRTZ.PROP_TYP;
        BPCACLDD.TERM_CD = DDCSFRTZ.TERM_CD;
        BPCACLDD.LOAN_TYPE = DDCSFRTZ.LOAN_TYP;
        BPCACLDD.WE_FLG = DDCSFRTZ.WE_FLG;
        BPCACLDD.DUTY_FREE = DDCSFRTZ.DT_FREE;
        CEP.TRC(SCCGWA, BPCACLDD.PROD_CD);
        CEP.TRC(SCCGWA, BPCACLDD.CI_TYPE);
        CEP.TRC(SCCGWA, BPCACLDD.FIN_TYP);
        CEP.TRC(SCCGWA, BPCACLDD.AC_TYP);
        CEP.TRC(SCCGWA, BPCACLDD.PROP_TYP);
        CEP.TRC(SCCGWA, BPCACLDD.TERM_CD);
        CEP.TRC(SCCGWA, BPCACLDD.LOAN_TYPE);
        CEP.TRC(SCCGWA, BPCACLDD.WE_FLG);
        CEP.TRC(SCCGWA, BPCACLDD.DUTY_FREE);
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACLDD;
        S000_CALL_BPZQCNGL();
        if (pgmRtn) return;
    }
    public void B080_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = DDCSFRTZ.AC;
        BPCUCNGM.KEY.CNTR_TYPE = DDCSFRTZ.CNTR_TYP;
        BPCUCNGM.PROD_TYPE = "" + DDCSFRTZ.PROP_TYP;
        JIBS_tmp_int = BPCUCNGM.PROD_TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCUCNGM.PROD_TYPE = "0" + BPCUCNGM.PROD_TYPE;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.AC);
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[1-1].GLMST);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[2-1].GLMST);
        S000_CALL_BPZUCNGM();
        if (pgmRtn) return;
    }
    public void T000_WRITE_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRADMN, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
        }
    }
    public void T000_REWRITE_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.REWRITE(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void T000_READ_UPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "ADP_NO = :DDRADMN.KEY.ADP_NO "
            + "AND ADP_TYPE = '8'";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADMN_FLG = 'F';
        } else {
            WS_ADMN_FLG = 'N';
        }
    }
    public void T000_READ_UPD_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_NO = :DDRADMN.KEY.ADP_NO "
            + "AND ADP_TYPE = :DDRADMN.KEY.ADP_TYPE "
            + "AND ADP_STS < > 'F'";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADMN_FLG = 'F';
        } else {
            WS_ADMN_FLG = 'N';
        }
    }
    public void T000_READ_UPD_DDTADMN_01() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_TYPE = '8' "
            + "AND ADP_STS < > 'F'";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADMN_FLG = 'F';
        } else {
            WS_ADMN_FLG = 'N';
        }
    }
    public void T000_READ_UPD_DDTADMN_02() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_NO = :DDRADMN.KEY.ADP_NO "
            + "AND ADP_TYPE = :DDRADMN.KEY.ADP_TYPE "
            + "AND ADP_STS = :DDRADMN.ADP_STS";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADMN_FLG = 'F';
        } else {
            WS_ADMN_FLG = 'N';
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
