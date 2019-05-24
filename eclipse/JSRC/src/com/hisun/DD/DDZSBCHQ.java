package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBCHQ {
    int JIBS_tmp_int;
    DBParm DDTVCH_RD;
    brParm DDTCHQ_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTSCHQ_RD;
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String K_OUTPUT_FMT = "DD401";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_CHQ_INVALID_DATE = "001";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String WS_ERR_MSG = " ";
    long WS_CUR_POS = 0;
    long WS_SBCHQ_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    String WS_PSW_FLG = " ";
    String WS_SBCHQ_NO = " ";
    String WS_AC_ATTR = " ";
    char WS_AC_STS = ' ';
    int WS_OPEN_BR = 0;
    String WS_AC_ENM = " ";
    String WS_AC_CNM = " ";
    String WS_AC_CCY = " ";
    char WS_AC_CCY_TYPE = ' ';
    String WS_CHQ_BV_CD = " ";
    String WS_AC_TYPE_FLG = " ";
    int WS_LEN = 0;
    int WS_TMP_DT = 0;
    int WS_TDT_DT = 0;
    char WS_SCHQ_FLG = ' ';
    char WS_CHQ_FLG = ' ';
    char WS_DDTVCH_FLG = ' ';
    String WS_DD_AC = " ";
    String WS_CHQ_NO = " ";
    String WS_BV_CD = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCOBCHQ DDCOBCHQ = new DDCOBCHQ();
    CICACCU CICACCU = new CICACCU();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    DDCUZFMM DDCUZFMM = new DDCUZFMM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRVCH DDRVCH = new DDRVCH();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCPCHQ DDCPCHQ = new DDCPCHQ();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    DDCUINQV DDCUINQV = new DDCUINQV();
    CICCUST CICCUST = new CICCUST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRSCHQ DDRSCHQ = new DDRSCHQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSBCHQ DDCSBCHQ;
    public void MP(SCCGWA SCCGWA, DDCSBCHQ DDCSBCHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBCHQ = DDCSBCHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSBCHQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQ);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B015_GET_AC_TYPE();
        if (WS_AC_TYPE_FLG.equalsIgnoreCase("DD")) {
            WS_AC_ATTR = "11";
            B020_GET_AC_INF_PROC();
            B030_CI_INF_PROC();
            CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
            if (DDCSBCHQ.CHQ_NO.trim().length() > 0) {
                if (DDCSBCHQ.CHQ_BV_CD.trim().length() > 0) {
                    if (!DDCSBCHQ.CHQ_BV_CD.equalsIgnoreCase("A")) {
                        B040_02_GET_CHQB_INF_PROC();
                    }
                } else {
                    B040_01_GET_CHQB_INF_PROC();
                }
            }
            B045_GET_CCY_INF_PROC();
            B050_CHECK_PSW_PROC();
            B060_OUTPUT_PROC();
        } else {
            B070_GET_IN_AC_INFO();
            B080_OUTPUT_PROC();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBCHQ.AC_NO);
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_BV_CD);
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
        CEP.TRC(SCCGWA, DDCSBCHQ.USE_DATE);
        CEP.TRC(SCCGWA, DDCSBCHQ.PSW);
        CEP.TRC(SCCGWA, DDCSBCHQ.AMT);
        if (DDCSBCHQ.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_GET_AC_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSBCHQ.AC_NO;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_AC_TYPE_FLG = "DD";
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_AC_TYPE_FLG = "IN";
            }
        }
    }
    public void B020_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSBCHQ.AC_NO;
        T000_READ_DDTMST();
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSBCHQ.AC_NO;
        CICQACAC.DATA.CCY_ACAC = "156";
        CICQACAC.DATA.CR_FLG = '1';
        S000_CALL_CICQACAC();
        WS_DD_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B030_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSBCHQ.AC_NO;
        S000_CALL_CISOACCU();
    }
    public void B040_01_GET_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        WS_LEN = 0;
        WS_LEN = DDCSBCHQ.CHQ_NO.trim().length();
        if (WS_LEN != 8 
            && WS_LEN != 16) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LEN_NOT_EQUAL_E;
            S000_ERR_MSG_PROC();
        }
        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
        if (!IBS.isNumeric(DDCSBCHQ.CHQ_NO.substring(0, WS_LEN))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_CHNL2);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        if (WS_CHQ_FLG == 'N') {
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && WS_LEN == 8) {
                T000_STARTBR_DDTCHQ1();
                T000_READNEXT_DDTCHQ();
                if (WS_CHQ_FLG == 'N') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        T000_ENDBR_DDTCHQ();
    }
    public void B040_02_GET_CHQB_INF_PROC() throws IOException,SQLException,Exception {
        WS_BV_CD = DDCSBCHQ.CHQ_BV_CD;
        CEP.TRC(SCCGWA, WS_DD_AC);
        CEP.TRC(SCCGWA, WS_CHQ_NO);
        CEP.TRC(SCCGWA, WS_BV_CD);
        WS_LEN = 0;
        WS_LEN = DDCSBCHQ.CHQ_NO.trim().length();
        if (WS_LEN != 8 
            && WS_LEN != 16) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LEN_NOT_EQUAL_E;
            S000_ERR_MSG_PROC();
        }
        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
        if (!IBS.isNumeric(DDCSBCHQ.CHQ_NO.substring(0, WS_LEN))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_LEN);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_CHNL2);
        T000_STARTBR_DDTCHQ2();
        T000_READNEXT_DDTCHQ();
        if (WS_CHQ_FLG == 'N') {
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && WS_LEN == 8) {
                T000_STARTBR_DDTCHQ3();
                T000_READNEXT_DDTCHQ();
                if (WS_CHQ_FLG == 'N') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                S000_ERR_MSG_PROC();
                CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
            }
        }
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        T000_ENDBR_DDTCHQ();
    }
    public void B045_GET_CCY_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCHQ.CCY);
        CEP.TRC(SCCGWA, DDRCHQ.CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCHQ.CHQ_BV_CD);
        WS_AC_CCY = DDRCHQ.CCY;
        WS_AC_CCY_TYPE = DDRCHQ.CCY_TYPE;
        WS_CHQ_BV_CD = DDRCHQ.CHQ_BV_CD;
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        IBS.init(SCCGWA, DDRCCY);
        if (DDRCHQ.CCY.trim().length() > 0 
            && DDRCHQ.CCY_TYPE != ' ') {
            DDRCCY.KEY.AC = WS_DD_AC;
            DDRCCY.CCY = DDRCHQ.CCY;
            DDRCCY.CCY_TYPE = DDRCHQ.CCY_TYPE;
            T000_READ_DDTCCY();
        } else {
            DDRCCY.KEY.AC = WS_DD_AC;
            T000_READ_DDTCCY_FIRST();
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
    }
    public void B050_CHECK_PSW_PROC() throws IOException,SQLException,Exception {
        if (DDCSBCHQ.CHQ_NO.trim().length() > 0 
            && DDCSBCHQ.ISU_DATE != 0) {
            IBS.init(SCCGWA, DDCUZFMM);
            DDCUZFMM.INPUT.AC_NO = DDCSBCHQ.AC_NO;
            DDCUZFMM.STD_AC = DDRMST.KEY.CUS_AC;
            DDCUZFMM.INPUT.CHQ_NO = DDCSBCHQ.CHQ_NO;
            CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.CHQ_TYP);
            if ((DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("00118") 
                    || DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("00022"))) {
                DDCUZFMM.INPUT.CHQ_TYP = '4';
            } else if (DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("00004")) {
                DDCUZFMM.INPUT.CHQ_TYP = '5';
            } else {
                DDCUZFMM.INPUT.CHQ_TYP = '1';
            }
            DDCUZFMM.INPUT.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
            if (DDCSBCHQ.CHQ_BV_CD.equalsIgnoreCase("A")) {
                DDCUZFMM.INPUT.CHQ_TYP = DDCSBCHQ.CHQ_BV_CD.charAt(0);
            }
            DDCUZFMM.INPUT.CHQ_ISSU_DATE = DDCSBCHQ.ISU_DATE;
            DDCUZFMM.INPUT.CHQ_PSW = DDCSBCHQ.PSW;
            DDCUZFMM.INPUT.AMT = DDCSBCHQ.AMT;
            S000_CALL_DDZUZFMM();
            CEP.TRC(SCCGWA, DDCUZFMM.OUTPUT_DATE.CHK_RESULT);
            if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '1') {
                WS_PSW_FLG = "03";
            } else if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '2') {
                WS_PSW_FLG = "04";
            } else if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '3') {
                WS_PSW_FLG = "02";
            } else if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '4') {
                WS_PSW_FLG = "01";
            } else {
                WS_PSW_FLG = "02";
            }
        }
    }
    public void T000_READ_DDTVCH_PROC() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        DDTVCH_RD.fst = true;
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTVCH_FLG = 'Y';
        } else {
            WS_DDTVCH_FLG = 'N';
        }
    }
    public void B070_GET_IN_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "RB070-GET-IN-AC-INFO-BEGIN");
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = DDCSBCHQ.AC_NO;
        S000_CALL_AIZPQMIB();
        WS_AC_ATTR = "01";
        if (AICPQMIB.OUTPUT_DATA.STS == 'S') {
            WS_AC_STS = 'N';
        } else {
            WS_AC_STS = AICPQMIB.OUTPUT_DATA.STS;
        }
        WS_OPEN_BR = AICPQMIB.INPUT_DATA.BR;
        WS_AC_ENM = AICPQMIB.OUTPUT_DATA.ENG_NM;
        WS_AC_CNM = AICPQMIB.OUTPUT_DATA.CHS_NM;
        WS_AC_CCY = AICPQMIB.INPUT_DATA.CCY;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.STS);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.BR);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.ENG_NM);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CHS_NM);
        CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.CCY);
    }
    public void B080_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOBCHQ);
        DDCOBCHQ.AC = DDCSBCHQ.AC_NO;
        DDCOBCHQ.CCY = WS_AC_CCY;
        DDCOBCHQ.CCY_TYPE = ' ';
        DDCOBCHQ.CHQ_BV_CD = " ";
        DDCOBCHQ.AC_STS = WS_AC_STS;
        DDCOBCHQ.AC_CNM = WS_AC_CNM;
        DDCOBCHQ.AC_ENM = WS_AC_ENM;
        DDCOBCHQ.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCOBCHQ.TR_BR = WS_OPEN_BR;
        DDCOBCHQ.PAY_MTH = '9';
        DDCOBCHQ.PSW_FLG = " ";
        DDCOBCHQ.AC_STS_WORD = " ";
        DDCOBCHQ.AC_ATTR = WS_AC_ATTR;
        DDCOBCHQ.CHQ_FLG = ' ';
        DDCOBCHQ.CHQ_STS = ' ';
        DDCOBCHQ.CHQ_NO = " ";
        DDCOBCHQ.CNAP_NO = 0;
        DDCOBCHQ.VAL_BAL = AICPQMIB.OUTPUT_DATA.CBAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOBCHQ;
        SCCFMT.DATA_LEN = 713;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = DDCSBCHQ.AC_NO;
        T000_READ_DDTVCH_PROC();
        IBS.init(SCCGWA, DDCOBCHQ);
        DDCOBCHQ.AC = DDCSBCHQ.AC_NO;
        CEP.TRC(SCCGWA, DDCOBCHQ.AC);
        CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        DDCOBCHQ.CCY = DDRCCY.CCY;
        DDCOBCHQ.CCY_TYPE = DDRCCY.CCY_TYPE;
        DDCOBCHQ.CHQ_BV_CD = WS_CHQ_BV_CD;
        DDCOBCHQ.AC_STS = DDRMST.AC_STS;
        DDCOBCHQ.AC_CNM = CICACCU.DATA.AC_CNM;
        DDCOBCHQ.AC_ENM = CICACCU.DATA.AC_ENM;
        if (DDCOBCHQ.AC_CNM.trim().length() == 0) {
            DDCOBCHQ.AC_CNM = CICACCU.DATA.CI_CNM;
        }
        if (DDCOBCHQ.AC_ENM.trim().length() == 0) {
            DDCOBCHQ.AC_ENM = CICACCU.DATA.CI_ENM;
        }
        if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
            IBS.init(SCCGWA, DCCUCINF);
            if (DCCPACTY.OUTPUT.SASB_AC_FLG == 'Y') {
                DCCUCINF.CARD_NO = DCCPACTY.OUTPUT.SASB_CARD_NO;
            } else {
                DCCUCINF.CARD_NO = DDCSBCHQ.AC_NO;
            }
            S000_CALL_DCZUCINF();
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CINO);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_IDTYP);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_IDNO);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_CNM);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_HLDR_ENM);
            CEP.TRC(SCCGWA, DCCUCINF.ADSC_TYPE);
            CEP.TRC(SCCGWA, DCCUCINF.JOIN_CUS_FLG);
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, DDCOBCHQ.AC_CNM);
        DDCOBCHQ.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCOBCHQ.OPEN_BR = DDRMST.OPEN_DP;
        DDCOBCHQ.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCOBCHQ.PSW_FLG = WS_PSW_FLG;
        DDCOBCHQ.AC_STS_WORD = DDRMST.AC_STS_WORD;
        DDCOBCHQ.AC_ATTR = WS_AC_ATTR;
        DDCOBCHQ.CI_FLG = DDRMST.CI_TYP;
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        CEP.TRC(SCCGWA, DDCOBCHQ.CI_FLG);
        CEP.TRC(SCCGWA, DDCOBCHQ.PSW_FLG);
        CEP.TRC(SCCGWA, DDCOBCHQ.PAY_MTH);
        CEP.TRC(SCCGWA, "111111111111");
        CEP.TRC(SCCGWA, DDCOBCHQ.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        DDCOBCHQ.VAL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, DDCOBCHQ.VAL_BAL);
        CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
        CEP.TRC(SCCGWA, DDCSBCHQ.USE_DATE);
        if (DDCSBCHQ.CHQ_NO.trim().length() == 0) {
        } else {
            if (DDCSBCHQ.ISU_DATE != 0 
                && DDCSBCHQ.USE_DATE != 0) {
                if (!DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("00118") 
                    && !DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("00004") 
                    && !DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("00022") 
                    && !DDRCHQ.CHQ_BV_CD.equalsIgnoreCase("A")) {
                    B090_CHK_CHQ_DATE_PROC();
                } else {
                    DDCOBCHQ.CHQ_FLG = '1';
                }
            }
        }
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        CEP.TRC(SCCGWA, "AAAAAAAAA");
        CEP.TRC(SCCGWA, "1111111111111111111");
        if (!DDCSBCHQ.CHQ_BV_CD.equalsIgnoreCase("A")) {
            if (DDCSBCHQ.CHQ_NO.trim().length() > 0) {
                if (WS_LEN != 8) {
                    if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
                    JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
                    if (DDCSBCHQ.CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_SBCHQ_CHQ_NO = 0;
                    else WS_SBCHQ_CHQ_NO = Long.parseLong(DDCSBCHQ.CHQ_NO.substring(0, WS_LEN));
                    if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                    JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                    if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                    else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN));
                } else {
                    if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                    JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                    if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() > 0) {
                        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
                        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
                        if (DDCSBCHQ.CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) WS_SBCHQ_CHQ_NO = 0;
                        else WS_SBCHQ_CHQ_NO = Long.parseLong(DDCSBCHQ.CHQ_NO.substring(9 - 1, 9 + 8 - 1));
                        if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                        JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                        if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                        else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1));
                    } else {
                        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
                        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
                        if (DDCSBCHQ.CHQ_NO.substring(0, 8).trim().length() == 0) WS_SBCHQ_CHQ_NO = 0;
                        else WS_SBCHQ_CHQ_NO = Long.parseLong(DDCSBCHQ.CHQ_NO.substring(0, 8));
                        if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                        JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                        if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                        else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8));
                    }
                }
                CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
                CEP.TRC(SCCGWA, WS_SBCHQ_CHQ_NO);
                CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
                WS_CUR_POS = WS_SBCHQ_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1;
                CEP.TRC(SCCGWA, WS_CUR_POS);
                if (WS_CUR_POS <= 50) {
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    DDCOBCHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).charAt(0);
                    if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                    JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                    for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    if (DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("1") 
                        && DDRMST.AC_STS_WORD.substring(64 - 1, 64 + 1 - 1).equalsIgnoreCase("1")) {
                        S000_MODCHQ_PROC();
                    }
                } else {
                    DDCOBCHQ.CHQ_STS = '1';
                }
                CEP.TRC(SCCGWA, DDCOBCHQ.CHQ_STS);
                if (WS_LEN == 8) {
                    if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                    JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                    for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                    if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() > 0) {
                        if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                        JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                        if (DDCOBCHQ.CHQ_NO == null) DDCOBCHQ.CHQ_NO = "";
                        JIBS_tmp_int = DDCOBCHQ.CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDCOBCHQ.CHQ_NO += " ";
                        DDCOBCHQ.CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8) + DDCOBCHQ.CHQ_NO.substring(8);
                        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
                        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
                        if (DDCOBCHQ.CHQ_NO == null) DDCOBCHQ.CHQ_NO = "";
                        JIBS_tmp_int = DDCOBCHQ.CHQ_NO.length();
                        for (int i=0;i<16-JIBS_tmp_int;i++) DDCOBCHQ.CHQ_NO += " ";
                        DDCOBCHQ.CHQ_NO = DDCOBCHQ.CHQ_NO.substring(0, 9 - 1) + DDCSBCHQ.CHQ_NO.substring(9 - 1, 9 + 8 - 1) + DDCOBCHQ.CHQ_NO.substring(9 + 8 - 1);
                        CEP.TRC(SCCGWA, "OUT");
                        CEP.TRC(SCCGWA, DDCOBCHQ.CHQ_NO);
                    } else {
                        DDCOBCHQ.CHQ_NO = DDCSBCHQ.CHQ_NO;
                    }
                } else {
                    DDCOBCHQ.CHQ_NO = DDCSBCHQ.CHQ_NO;
                    CEP.TRC(SCCGWA, DDCOBCHQ.CHQ_NO);
                }
            }
        }
        CEP.TRC(SCCGWA, DDCOBCHQ.CHQ_NO);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = DDRMST.OPEN_DP;
        S000_CALL_BPZPQORG();
        DDCOBCHQ.CNAP_NO = BPCPQORG.CNAP_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOBCHQ;
        SCCFMT.DATA_LEN = 713;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_MODCHQ_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTSCHQ1();
        if (WS_SCHQ_FLG == 'Y' 
            && DDRSCHQ.CHQ_STS == '0') {
            CEP.TRC(SCCGWA, DDCSBCHQ.AMT);
            if (DDCSBCHQ.AMT >= DDRSCHQ.AMT) {
                T000_READ_DDTSCHQ();
                if (WS_SCHQ_FLG == 'Y' 
                    && DDRSCHQ.CHQ_STS == '0') {
                    CEP.TRC(SCCGWA, DDRSCHQ.AMT);
                    if (DDCSBCHQ.AMT != DDRSCHQ.AMT) {
                        DDCOBCHQ.CHQ_STS = '7';
                    }
                } else {
                    DDCOBCHQ.CHQ_STS = '7';
                }
            }
        }
    }
    public void B090_CHK_CHQ_DATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
        CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        DDCOBCHQ.CHQ_FLG = '1';
        if (DDCSBCHQ.USE_DATE != 0 
            && DDCSBCHQ.ISU_DATE > DDCSBCHQ.USE_DATE) {
            DDCOBCHQ.CHQ_FLG = '2';
        } else {
            if (DDCSBCHQ.ISU_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                DDCOBCHQ.CHQ_FLG = '2';
            }
        }
        R000_GET_CHQ_VALID_DT_PARM();
        if (DDCSBCHQ.USE_DATE != 0) {
            WS_TDT_DT = DDCSBCHQ.USE_DATE;
        } else {
            WS_TDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, WS_TDT_DT);
        CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
        if (DDCSBCHQ.ISU_DATE <= WS_TDT_DT) {
            IBS.init(SCCGWA, BPCPQBNK);
            BPCPQBNK.DATA_INFO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
            S000_CALL_BPZPQBNK();
            CEP.TRC(SCCGWA, "0000000000000000000");
            IBS.init(SCCGWA, BPCOCLWD);
            if (DDRMST.CI_TYP == '1') {
                BPCOCLWD.CAL_CODE = BPCPQBNK.DATA_INFO.CALD_BUI;
            } else {
                BPCOCLWD.CAL_CODE = BPCPQBNK.DATA_INFO.CALD_PUB;
            }
            CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.CALD_BUI);
            CEP.TRC(SCCGWA, BPCRBANK.CALD_BUI);
            CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.CALD_PUB);
            CEP.TRC(SCCGWA, BPCRBANK.CALD_PUB);
            CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
            BPCOCLWD.DATE1 = DDCSBCHQ.ISU_DATE;
            CEP.TRC(SCCGWA, DDCPCHQ.DATA_TXT.DAYS);
            BPCOCLWD.DAYS = DDCPCHQ.DATA_TXT.DAYS;
            S000_CALL_BPZPCLWD();
            WS_TMP_DT = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            CEP.TRC(SCCGWA, WS_TMP_DT);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_FLG);
            if (BPCOCLWD.DATE2 < WS_TDT_DT 
                && BPCOCLWD.DATE2_FLG != 'H') {
                DDCOBCHQ.CHQ_FLG = '2';
            }
            if (BPCOCLWD.DATE2 < WS_TDT_DT 
                && BPCOCLWD.DATE2_FLG == 'H') {
                IBS.init(SCCGWA, BPCOCLWD);
                if (DDRMST.CI_TYP == '1') {
                    BPCOCLWD.CAL_CODE = BPCPQBNK.DATA_INFO.CALD_BUI;
                } else {
                    BPCOCLWD.CAL_CODE = BPCPQBNK.DATA_INFO.CALD_PUB;
                }
                BPCOCLWD.DATE1 = WS_TMP_DT;
                BPCOCLWD.WDAYS = 1;
                S000_CALL_BPZPCLWD();
                CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
                if (BPCOCLWD.DATE2 < WS_TDT_DT) {
                    DDCOBCHQ.CHQ_FLG = '2';
                }
            }
        }
        CEP.TRC(SCCGWA, DDCOBCHQ.CHQ_FLG);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        CEP.TRC(SCCGWA, DDCSBCHQ.USE_DATE);
    }
    public void R000_GET_CHQ_VALID_DT_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, DDCPCHQ);
        DDCPCHQ.KEY.TYP = "PDD04";
        DDCPCHQ.KEY.CD = K_CHQ_INVALID_DATE;
        BPCPRMR.DAT_PTR = DDCPCHQ;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, BPCPRMR);
        CEP.TRC(SCCGWA, DDCPCHQ);
        CEP.TRC(SCCGWA, DDCPCHQ.DATA_TXT.DAYS);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, WS_CHQ_FLG);
        DDRCHQ.KEY.AC = WS_DD_AC;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSBCHQ.CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_STARTBR_DDTCHQ1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
        if (DDCSBCHQ.CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) {
            WS_SBCHQ_NO = DDCSBCHQ.CHQ_NO;
            DDCSBCHQ.CHQ_NO = " ";
            if (WS_SBCHQ_NO == null) WS_SBCHQ_NO = "";
            JIBS_tmp_int = WS_SBCHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) WS_SBCHQ_NO += " ";
            if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
            JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
            DDCSBCHQ.CHQ_NO = DDCSBCHQ.CHQ_NO.substring(0, 9 - 1) + WS_SBCHQ_NO.substring(0, 8) + DDCSBCHQ.CHQ_NO.substring(9 + 8 - 1);
        }
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        WS_CHQ_FLG = 'N';
        DDRCHQ.KEY.AC = WS_DD_AC;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSBCHQ.CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND SUBSTR ( STR_CHQ_NO , 9 , 8 ) <= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 9 , 8 ) "
            + "AND SUBSTR ( END_CHQ_NO , 9 , 8 ) >= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 9 , 8 )";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_STARTBR_DDTCHQ2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        WS_CHQ_FLG = 'N';
        DDRCHQ.KEY.AC = WS_DD_AC;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSBCHQ.CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND CHQ_BV_CD = :WS_BV_CD";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_STARTBR_DDTCHQ3() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
        JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
        for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
        if (DDCSBCHQ.CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) {
            WS_SBCHQ_NO = DDCSBCHQ.CHQ_NO;
            DDCSBCHQ.CHQ_NO = " ";
            if (WS_SBCHQ_NO == null) WS_SBCHQ_NO = "";
            JIBS_tmp_int = WS_SBCHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) WS_SBCHQ_NO += " ";
            if (DDCSBCHQ.CHQ_NO == null) DDCSBCHQ.CHQ_NO = "";
            JIBS_tmp_int = DDCSBCHQ.CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSBCHQ.CHQ_NO += " ";
            DDCSBCHQ.CHQ_NO = DDCSBCHQ.CHQ_NO.substring(0, 9 - 1) + WS_SBCHQ_NO.substring(0, 8) + DDCSBCHQ.CHQ_NO.substring(9 + 8 - 1);
        }
        CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_NO);
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, WS_CHQ_FLG);
        DDRCHQ.KEY.AC = WS_DD_AC;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSBCHQ.CHQ_NO;
        CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND SUBSTR ( STR_CHQ_NO , 9 , 8 ) <= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 9 , 8 ) "
            + "AND SUBSTR ( END_CHQ_NO , 9 , 8 ) >= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 9 , 8 ) "
            + "AND CHQ_BV_CD = :WS_BV_CD";
        DDTCHQ_BR.rp.order = "STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
            CEP.TRC(SCCGWA, WS_CHQ_FLG);
        } else {
            WS_CHQ_FLG = 'N';
            CEP.TRC(SCCGWA, WS_CHQ_FLG);
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTSCHQ1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSCHQ);
        DDRSCHQ.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRSCHQ.KEY.TYP = '1';
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.col = "CHQ_STS,AMT";
        DDTSCHQ_RD.where = "AC = :DDRSCHQ.KEY.AC "
            + "AND TYP = :DDRSCHQ.KEY.TYP";
        IBS.READ(SCCGWA, DDRSCHQ, this, DDTSCHQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SCHQ_FLG = 'Y';
        } else {
            WS_SCHQ_FLG = 'N';
        }
    }
    public void T000_READ_DDTSCHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSCHQ);
        DDRSCHQ.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
        DDRSCHQ.KEY.TYP = '2';
        DDRSCHQ.KEY.CHQ_NO = DDCSBCHQ.CHQ_NO;
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.col = "CHQ_STS,AMT";
        IBS.READ(SCCGWA, DDRSCHQ, DDTSCHQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SCHQ_FLG = 'Y';
        } else {
            WS_SCHQ_FLG = 'N';
            if (WS_LEN == 8) {
                DDRSCHQ.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
                DDRSCHQ.KEY.TYP = '2';
                DDRSCHQ.KEY.CHQ_NO = DDCSBCHQ.CHQ_NO;
                DDTSCHQ_RD = new DBParm();
                DDTSCHQ_RD.TableName = "DDTSCHQ";
                DDTSCHQ_RD.col = "CHQ_STS,AMT";
                DDTSCHQ_RD.where = "AC = :DDRSCHQ.KEY.AC "
                    + "AND TYP = :DDRSCHQ.KEY.TYP "
                    + "AND SUBSTR ( CHQ_NO , 9 , 8 ) = :DDRSCHQ.KEY.CHQ_NO";
                IBS.READ(SCCGWA, DDRSCHQ, this, DDTSCHQ_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_SCHQ_FLG = 'Y';
                } else {
                    WS_SCHQ_FLG = 'N';
                }
            }
            if (WS_LEN == 16) {
                DDRSCHQ.KEY.AC = DCCPACTY.OUTPUT.STD_AC;
                DDRSCHQ.KEY.TYP = '2';
                DDRSCHQ.KEY.CHQ_NO = DDCSBCHQ.CHQ_NO;
                DDTSCHQ_RD = new DBParm();
                DDTSCHQ_RD.TableName = "DDTSCHQ";
                DDTSCHQ_RD.col = "CHQ_STS,AMT";
                DDTSCHQ_RD.where = "AC = :DDRSCHQ.KEY.AC "
                    + "AND TYP = :DDRSCHQ.KEY.TYP "
                    + "AND SUBSTR ( CHQ_NO , 1 , 8 ) = SUBSTR ( :DDRSCHQ.KEY.CHQ_NO , 9 , 8 )";
                IBS.READ(SCCGWA, DDRSCHQ, this, DDTSCHQ_RD);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_SCHQ_FLG = 'Y';
                } else {
                    WS_SCHQ_FLG = 'N';
                }
            }
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, "GSQ11");
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCPQORG.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CICQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUINQV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-VSAC", DDCUINQV, true);
        if (DDCUINQV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCUINQV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUERY_BANK, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUZFMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUZFMM", DDCUZFMM);
        CEP.TRC(SCCGWA, DDCUZFMM.RC.RC_CODE);
        if (DDCUZFMM.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + DDCUZFMM.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
