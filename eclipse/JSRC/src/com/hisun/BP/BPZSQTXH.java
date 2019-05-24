package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQTXH {
    brParm SCTJRN_BR = new brParm();
    BPCD57_VCH_ITM VCH_ITM;
    String JIBS_tmp_str[] = new String[10];
    VCHO_VCH_AREA VCH_AREA;
    DBParm SCTJRNH_RD;
    boolean pgmRtn = false;
    String K_CMP_INQ_JRN = "SC-JOURNAL-MAINTAIN";
    String K_CMP_INQ_JRN_HIS = "SC-JOURNAL-MAINTAIN";
    String K_CMP_INQ_INP = "SC-LARGE-INPUT-MAINT";
    String K_CMP_OUTPUT = "SC-CHG-I-TABLE-OUTP";
    String K_CMP_INQ_TLR = "BP-F-TLR-INF-QUERY ";
    String K_CMP_INQ_VCH = "BP-R-MAINT-VCHT    ";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FEE_REF = "FEE HISTORY";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP657";
    String K_FMT_CD_3 = "BP081";
    String K_FMT_CHNL = "BP002";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPZSQTXH_WS_RC WS_RC = new BPZSQTXH_WS_RC();
    String WS_ERR_INFO = " ";
    BPZSQTXH_WS_CHNL_INFO WS_CHNL_INFO = new BPZSQTXH_WS_CHNL_INFO();
    short WS_I = 0;
    short WS_CNT = 0;
    short WS_TR_CNT = 0;
    int WS_AC_DT = 0;
    long WS_JRN_KEY = 0;
    long WS_VCH_JRNNO = 0;
    char WS_BROWSE_FUNC = ' ';
    BPZSQTXH_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSQTXH_WS_OUTPUT_DATA();
    char WS_STOP_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_HIS_FLG = ' ';
    char WS_VCH_STOP_FLG = ' ';
    char WS_OUT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCD57 BPCD57 = new BPCD57();
    BPRVWA BPRVCHO = new BPRVWA();
    BPCOVINQ BPCOVINQ = new BPCOVINQ();
    SCRINP SCRINP = new SCRINP();
    SCRINPH SCRINPH = new SCRINPH();
    SCCPINP SCCPINP = new SCCPINP();
    SCRJRN SCRJRN = new SCRJRN();
    SCRJRNH SCRJRNH = new SCRJRNH();
    BPRFFHIS BPRFFHIS = new BPRFFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCOFEEL BPCOFEEL = new BPCOFEEL();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    SCCPJRN SCCPJRN = new SCCPJRN();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCOUT SCCCOUT = new SCCCOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    int WS_DB_TR_BRANCH = 0;
    int WS_DB_AC_DT = 0;
    SCCGWA SCCGWA;
    BPCSQTXH BPCSQTXH;
    public void MP(SCCGWA SCCGWA, BPCSQTXH BPCSQTXH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQTXH = BPCSQTXH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSQTXH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_RC);
        IBS.init(SCCGWA, BPCOVINQ);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, WS_RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSQTXH);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.JNO);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.AC_DT);
        CEP.TRC(SCCGWA, BPCSQTXH.FUNC);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.AP_CODE);
        if (BPCSQTXH.FUNC == 'Q') {
            if (BPCSQTXH.DATA.JNO == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_JNO);
            }
        }
        if (BPCSQTXH.DATA.AC_DT == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_DT_ERR);
        }
        if (BPCSQTXH.DATA.AC_DT > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE);
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B210_COMM_PROC();
        if (pgmRtn) return;
        if (BPCSQTXH.FUNC == 'B'
            || BPCSQTXH.FUNC == 'T') {
            B220_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCSQTXH.FUNC == 'Q') {
            B230_QUERY_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR);
        }
    }
    public void B210_COMM_PROC() throws IOException,SQLException,Exception {
        if (BPCSQTXH.DATA.TR_CNT == 0) {
            BPCSQTXH.DATA.TR_CNT = 500;
        }
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.AC_DT);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.JNO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCSQTXH.DATA.JNO != 0 
            || BPCSQTXH.DATA.AC_DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_JRN_KEY = BPCSQTXH.DATA.JNO;
            CEP.TRC(SCCGWA, BPCSQTXH.DATA.JNO);
        }
        WS_AC_DT = BPCSQTXH.DATA.AC_DT;
    }
    public void B220_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.JNO);
        B220_BROWSE_BY_DATE_PROC();
        if (pgmRtn) return;
    }
    public void B220_BROWSE_BY_JRN_PROC() throws IOException,SQLException,Exception {
        S000_WRITE_TSQ_TITLE();
        if (pgmRtn) return;
        WS_TR_CNT = 0;
        if (BPCSQTXH.DATA.JNO != 0) {
            IBS.init(SCCGWA, SCCPJRN);
            IBS.init(SCCGWA, SCRJRN);
            SCRJRN.AC_DATE = BPCSQTXH.DATA.AC_DT;
            SCRJRN.KEY.JRN_NO = BPCSQTXH.DATA.JNO;
            SCCPJRN.FUNC = '1';
            S000_CALL_SCZPJRN();
            if (pgmRtn) return;
            B221_OUTPUT_DETAIL_INFO();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCRJRN);
            SCRJRN.AC_DATE = BPCSQTXH.DATA.AC_DT;
            SCRJRN.TLR_ID = BPCSQTXH.DATA.TL_ID;
            CEP.TRC(SCCGWA, BPCSQTXH.DATA.TL_ID);
            CEP.TRC(SCCGWA, BPCSQTXH.DATA.AP_CODE);
            CEP.TRC(SCCGWA, BPCSQTXH.DATA.TR_CODE);
            if (BPCSQTXH.DATA.TL_ID.trim().length() > 0 
                && BPCSQTXH.DATA.AP_CODE == 0 
                && BPCSQTXH.DATA.TR_CODE == 0) {
                CEP.TRC(SCCGWA, "0404");
                T210_STARTBR_SCTJRN_04();
                if (pgmRtn) return;
                T210_REMAIN_SCTJRN();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_ONLY_TLR_JRN);
            }
        }
    }
    public void T210_STARTBR_SCTJRN_04() throws IOException,SQLException,Exception {
        SCTJRN_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') SCTJRN_BR.rp.TableName = "SCTJRN1";
        else SCTJRN_BR.rp.TableName = "SCTJRN2";
        SCTJRN_BR.rp.where = "TLR_ID = :SCRJRN.TLR_ID";
        IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN_BR);
    }
    public void T210_STARTBR_SCTJRN_06() throws IOException,SQLException,Exception {
        SCTJRN_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') SCTJRN_BR.rp.TableName = "SCTJRN1";
        else SCTJRN_BR.rp.TableName = "SCTJRN2";
        SCTJRN_BR.rp.where = "AC_DATE = :SCRJRN.AC_DATE";
        IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN_BR);
    }
    public void T210_STARTBR_SCTJRN_07() throws IOException,SQLException,Exception {
        SCTJRN_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') SCTJRN_BR.rp.TableName = "SCTJRN1";
        else SCTJRN_BR.rp.TableName = "SCTJRN2";
        SCTJRN_BR.rp.where = "AC_DATE = :WS_DB_AC_DT "
            + "AND TR_BRANCH = :WS_DB_TR_BRANCH";
        IBS.STARTBR(SCCGWA, SCRJRN, this, SCTJRN_BR);
    }
    public void T210_REMAIN_SCTJRN() throws IOException,SQLException,Exception {
        T210_READNEXT_SCTJRN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TR_CNT);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.TR_CNT);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_TR_CNT < BPCSQTXH.DATA.TR_CNT 
            && SCCMPAG.FUNC != 'E' 
            && WS_STOP_FLG != 'Y') {
            WS_OUT = 'N';
            if (BPCSQTXH.DATA.TL_ID.trim().length() > 0) {
                if (SCRJRN.TLR_ID.equalsIgnoreCase(BPCSQTXH.DATA.TL_ID)) {
                    WS_OUT = 'Y';
                } else {
                    WS_OUT = 'N';
                }
            } else {
                WS_OUT = 'Y';
            }
            if (WS_OUT == 'Y') {
                if (BPCSQTXH.DATA.JNO != 0) {
                    if (SCRJRN.KEY.JRN_NO != BPCSQTXH.DATA.JNO) {
                        WS_OUT = 'N';
                    }
                }
            }
            if (WS_OUT == 'Y') {
                B221_OUTPUT_DETAIL_INFO();
                if (pgmRtn) return;
            }
            T210_READNEXT_SCTJRN();
            if (pgmRtn) return;
        }
        T210_ENDBR_SCTJRN();
        if (pgmRtn) return;
    }
    public void T210_READNEXT_SCTJRN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, SCRJRN, this, SCTJRN_BR);
    }
    public void T210_ENDBR_SCTJRN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SCTJRN_BR);
    }
    public void B220_BROWSE_BY_DATE_PROC() throws IOException,SQLException,Exception {
        S000_WRITE_TSQ_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCRJRNH.KEY.JRN_NO);
        CEP.TRC(SCCGWA, SCRJRNH.TLR_CHAN);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.TR_CNT);
        CEP.TRC(SCCGWA, SCRJRNH.TR_AP);
        CEP.TRC(SCCGWA, SCRJRNH.TR_CODE);
        CEP.TRC(SCCGWA, SCRJRNH.TLR_ID);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.AP_CODE);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.TR_CODE);
        CEP.TRC(SCCGWA, SCRJRNH.KEY.JRN_NO);
        CEP.TRC(SCCGWA, SCRJRNH.TLR_CHAN);
        CEP.TRC(SCCGWA, BPCSQTXH.DATA.TR_CNT);
        WS_TR_CNT = 0;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCSQTXH.DATA.TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_DB_TR_BRANCH = BPCSQTXH.DATA.TR_BRANCH;
        WS_DB_AC_DT = BPCSQTXH.DATA.AC_DT;
        T210_STARTBR_SCTJRN_07();
        if (pgmRtn) return;
        T210_REMAIN_SCTJRN();
        if (pgmRtn) return;
    }
    public void B221_OUTPUT_DETAIL_INFO() throws IOException,SQLException,Exception {
        WS_VCH_JRNNO = SCRJRN.KEY.JRN_NO;
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        CEP.TRC(SCCGWA, WS_VCH_JRNNO);
        CEP.TRC(SCCGWA, SCRJRN.VCH_CNT);
        if (BPCSQTXH.FUNC == 'B') {
            B221_01_OUTPUT_CTL_TD();
            if (pgmRtn) return;
            WS_TR_CNT += 1;
        } else {
            B221_01_OUTPUT_CTL_TD();
            if (pgmRtn) return;
        }
    }
    public void B221_01_OUTPUT_CTL_TD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_OT_TR_TIME = SCRJRN.TR_TIME;
        WS_OUTPUT_DATA.WS_OT_TR_CODE.WS_OT_AP = SCRJRN.TR_AP;
        WS_OUTPUT_DATA.WS_OT_TR_CODE.WS_OT_TX_CD = SCRJRN.TR_CODE;
        WS_OUTPUT_DATA.WS_OT_JRN_NO = SCRJRN.KEY.JRN_NO;
        WS_OUTPUT_DATA.WS_OT_SUP1_ID = SCRJRN.SUP1_ID;
        WS_OUTPUT_DATA.WS_OT_SUP2_ID = SCRJRN.SUP2_ID;
        WS_OUTPUT_DATA.WS_OT_AC_DT = WS_AC_DT;
        WS_OUTPUT_DATA.WS_OT_VCH_NO = SCRJRN.VCH_NO;
        CEP.TRC(SCCGWA, SCRJRN.VCH_NO);
        WS_OUTPUT_DATA.WS_OT_CHNL = SCRJRN.CHNL;
        WS_OUTPUT_DATA.WS_OT_TLR_ID = SCRJRN.TLR_ID;
        if (SCRJRN.CANCEL_IND == 'Y' 
            || SCRJRN.CANCEL_JRN_NO != 0) {
            WS_OUTPUT_DATA.WS_OT_TR_TYPE = '*';
        }
        S000_WRITE_TSQ_DETAIL();
        if (pgmRtn) return;
    }
    public void B221_02_OUTPUT_FIN_TD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_OT_TR_TIME = SCRJRN.TR_TIME;
        WS_OUTPUT_DATA.WS_OT_JRN_NO = SCRJRN.KEY.JRN_NO;
        WS_OUTPUT_DATA.WS_OT_TR_CODE.WS_OT_AP = SCRJRN.TR_AP;
        WS_OUTPUT_DATA.WS_OT_TR_CODE.WS_OT_TX_CD = SCRJRN.TR_CODE;
        WS_OUTPUT_DATA.WS_OT_SUP1_ID = SCRJRN.SUP1_ID;
        WS_OUTPUT_DATA.WS_OT_SUP2_ID = SCRJRN.SUP2_ID;
        WS_OUTPUT_DATA.WS_OT_VCH_NO = SCRJRN.VCH_NO;
        WS_OUTPUT_DATA.WS_OT_VCH_CCY = BPRVCHO.VCH_AREA.get(WS_CNT-1).PARTB.CCY;
        if (BPRVCHO.VCH_AREA.get(WS_CNT-1).PARTB.ITM.trim().length() == 0) WS_OUTPUT_DATA.WS_OT_VCH_ACCT = 0;
        else WS_OUTPUT_DATA.WS_OT_VCH_ACCT = Long.parseLong(BPRVCHO.VCH_AREA.get(WS_CNT-1).PARTB.ITM);
        WS_OUTPUT_DATA.WS_OT_VCH_AC = BPRVCHO.VCH_AREA.get(WS_CNT-1).PARTB.AC_NO;
        WS_OUTPUT_DATA.WS_OT_VCH_AMT = BPRVCHO.VCH_AREA.get(WS_CNT-1).PARTB.AMT;
        WS_OUTPUT_DATA.WS_OT_VCH_SIGN = BPRVCHO.VCH_AREA.get(WS_CNT-1).PARTB.SIGN;
        WS_OUTPUT_DATA.WS_OT_AC_DT = WS_AC_DT;
        WS_OUTPUT_DATA.WS_OT_CHNL = SCRJRN.CHNL;
        WS_OUTPUT_DATA.WS_OT_TLR_ID = SCRJRN.TLR_ID;
        if (SCRJRN.CANCEL_IND == 'Y' 
            || SCRJRN.CANCEL_JRN_NO != 0) {
            WS_OUTPUT_DATA.WS_OT_TR_TYPE = '*';
        }
        S000_WRITE_TSQ_DETAIL();
        if (pgmRtn) return;
    }
    public void B221_OUTPUT_DETAIL_INFO_02() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111111");
        WS_VCH_JRNNO = SCRJRNH.KEY.JRN_NO;
        CEP.TRC(SCCGWA, SCRJRNH.KEY.JRN_NO);
        CEP.TRC(SCCGWA, WS_VCH_JRNNO);
        CEP.TRC(SCCGWA, SCRJRNH.VCH_CNT);
        if (BPCSQTXH.FUNC == 'B') {
            B221_01_OUTPUT_CTL_TD_02();
            if (pgmRtn) return;
            WS_TR_CNT += 1;
        } else {
            B221_01_OUTPUT_CTL_TD_02();
            if (pgmRtn) return;
        }
    }
    public void B221_01_OUTPUT_CTL_TD_02() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_OT_TR_TIME = SCRJRNH.TR_TIME;
        WS_OUTPUT_DATA.WS_OT_TR_CODE.WS_OT_AP = SCRJRNH.TR_AP;
        WS_OUTPUT_DATA.WS_OT_TR_CODE.WS_OT_TX_CD = SCRJRNH.TR_CODE;
        WS_OUTPUT_DATA.WS_OT_JRN_NO = SCRJRNH.KEY.JRN_NO;
        WS_OUTPUT_DATA.WS_OT_SUP1_ID = SCRJRNH.SUP1_ID;
        WS_OUTPUT_DATA.WS_OT_SUP2_ID = SCRJRNH.SUP2_ID;
        WS_OUTPUT_DATA.WS_OT_AC_DT = WS_AC_DT;
        WS_OUTPUT_DATA.WS_OT_VCH_NO = SCRJRNH.VCH_NO;
        CEP.TRC(SCCGWA, SCRJRNH.VCH_NO);
        WS_OUTPUT_DATA.WS_OT_CHNL = SCRJRNH.CHNL;
        WS_OUTPUT_DATA.WS_OT_TLR_ID = SCRJRNH.TLR_ID;
        if (SCRJRNH.CANCEL_IND == 'Y' 
            || SCRJRNH.CANCEL_JRN_NO != 0) {
            WS_OUTPUT_DATA.WS_OT_TR_TYPE = '*';
        }
        S000_WRITE_TSQ_DETAIL();
        if (pgmRtn) return;
    }
    public void B225_GET_TLR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSQTXH.DATA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.NEW_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CANNOT_INQ_OTH_BR);
        }
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.LAST_JRN);
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_DT);
        if (BPCFTLRQ.INFO.LAST_JRN == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_TX_FOUND);
        }
    }
    public void B230_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPJRN);
        IBS.init(SCCGWA, SCRJRN);
        SCRJRN.AC_DATE = WS_AC_DT;
        SCRJRN.KEY.JRN_NO = WS_JRN_KEY;
        SCCPJRN.FUNC = '1';
        CEP.TRC(SCCGWA, WS_JRN_KEY);
        CEP.TRC(SCCGWA, WS_AC_DT);
        S000_CALL_SCZPJRN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSQTXH.OUTPUT_FLG);
        if (BPCSQTXH.OUTPUT_FLG == 'N') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = SCRJRN.TR_AP;
            SCCSUBS.TR_CODE = SCRJRN.TR_CODE;
            CEP.TRC(SCCGWA, SCRJRN.TR_AP);
            CEP.TRC(SCCGWA, SCRJRN.TR_CODE);
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCRJRN.LINP_IND);
            CEP.TRC(SCCGWA, WS_JRN_KEY);
            if (SCRJRN.LINP_IND == '1') {
                S000_CALL_SCZPINP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, SCCCOUT);
                SCCCOUT.TR_ID.AP_MMO = SCRJRN.TR_AP_MMO;
                SCCCOUT.TR_ID.TR_CODE = SCRJRN.TR_CODE;
                CEP.TRC(SCCGWA, WS_HIS_FLG);
                CEP.TRC(SCCGWA, SCRINP.BLOB_DATA.trim().length());
                if (WS_HIS_FLG == 'Y') {
                    SCCCOUT.DATA_PTR = SCRINPH.BLOB_DATA;
                    CEP.TRC(SCCGWA, SCRINPH.BLOB_DATA);
                } else {
                    SCCCOUT.DATA_PTR = SCRINP.BLOB_DATA;
                }
                SCCCOUT.FMT = K_FMT_CD_1;
                CEP.TRC(SCCGWA, SCCCOUT.FMT);
                CEP.TRC(SCCGWA, SCCCOUT.DATA_LEN);
                if (SCCCOUT.DATA_LEN == 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UNNORMAL_JRNDATA, SCRJRN.SERV_CODE);
                } else {
                    S000_CALL_SCZCOUT();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, SCRJRN.TR_AP_MMO);
                IBS.init(SCCGWA, SCCCOUT);
                SCCCOUT.TR_ID.AP_MMO = SCRJRN.TR_AP_MMO;
                SCCCOUT.TR_ID.TR_CODE = SCRJRN.TR_CODE;
                SCCCOUT.DATA_PTR = SCRJRN.BLOB_INP_DATA;
                SCCCOUT.FMT = K_FMT_CD_1;
                CEP.TRC(SCCGWA, SCRJRN.BLOB_INP_DATA);
                CEP.TRC(SCCGWA, SCCCOUT.DATA_LEN);
                CEP.TRC(SCCGWA, SCCCOUT.DATA_LEN);
                if (SCCCOUT.DATA_LEN == 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_UNNORMAL_JRNDATA, SCRJRN.SERV_CODE);
                } else {
                    S000_CALL_SCZCOUT();
                    if (pgmRtn) return;
                }
            }
            R000_OUTPUT_CHNL_INFO();
            if (pgmRtn) return;
            R000_FEE_INFO_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCD57.COMM_DATA);
            BPCD57.VCH_DATA.VCH_CNT = 0;
            B231_FMT_DATA_TO_D57();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_2;
            SCCFMT.DATA_PTR = BPCD57;
            SCCFMT.DATA_LEN = 8311;
            IBS.FMT(SCCGWA, SCCFMT);
            if (SCRJRN.VCH_CNT != 0) {
                WS_VCH_JRNNO = SCRJRN.KEY.JRN_NO;
                R000_GET_VCH_INFO();
                if (pgmRtn) return;
            }
        }
    }
    public void B231_FMT_DATA_TO_D57() throws IOException,SQLException,Exception {
        BPCD57.COMM_DATA.AC_DATE = SCRJRN.AC_DATE;
        BPCD57.COMM_DATA.JRN_NO = SCRJRN.KEY.JRN_NO;
        BPCD57.COMM_DATA.AP_CODE = SCRJRN.TR_AP;
        BPCD57.COMM_DATA.TR_CODE = SCRJRN.TR_CODE;
        BPCD57.COMM_DATA.TR_DATE = SCRJRN.TR_DATE;
        BPCD57.COMM_DATA.TR_TIME = SCRJRN.TR_TIME;
        BPCD57.COMM_DATA.TM_NO = SCRJRN.TERM_ID;
        BPCD57.COMM_DATA.TL_ID = SCRJRN.TLR_ID;
        BPCD57.COMM_DATA.SUP1_ID = SCRJRN.SUP1_ID;
        BPCD57.COMM_DATA.SUP2_ID = SCRJRN.SUP2_ID;
        BPCD57.COMM_DATA.BR = SCRJRN.TR_BRANCH;
        BPCD57.COMM_DATA.REEN_FLAG = SCRJRN.REEN_IND;
        BPCD57.COMM_DATA.CTR_JRN_NO = SCRJRN.CANCEL_JRN_NO;
        IBS.CPY2CLS(SCCGWA, SCRJRN.AUTH_RESN, BPCD57.COMM_DATA.AUTH_REASON);
        BPCD57.VCH_DATA.TR_DATA.VCH_NO = SCRJRN.VCH_NO;
        CEP.TRC(SCCGWA, BPCD57.VCH_DATA.TR_DATA.VCH_NO);
        if (SCRJRN.VCH_CNT != 0) {
            BPCD57.VCH_DATA.VCH_CNT = (short) BPRVCHO.BASIC_AREA.CNT;
            VCH_ITM = new BPCD57_VCH_ITM();
            BPCD57.VCH_DATA.TR_DATA.VCH_ITM.add(VCH_ITM);
            for (WS_I = 1; WS_I <= BPRVCHO.BASIC_AREA.CNT; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.BR);
                CEP.TRC(SCCGWA, BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.CCY);
                CEP.TRC(SCCGWA, BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.AC_NO);
                CEP.TRC(SCCGWA, BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.SIGN);
                CEP.TRC(SCCGWA, BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.AMT);
                VCH_ITM.VCH_BR = BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.BR;
                VCH_ITM.VCH_CCY = BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.CCY;
                VCH_ITM.VCH_PRCD = BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.PROD_CODE;
                if (BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.ITM.trim().length() == 0) VCH_ITM.VCH_ACCT = 0;
                else VCH_ITM.VCH_ACCT = Long.parseLong(BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.ITM);
                VCH_ITM.VCH_AC = BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.AC_NO;
                VCH_ITM.VCH_SIGN = BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.SIGN;
                VCH_ITM.VCH_AMT = BPRVCHO.VCH_AREA.get(WS_I-1).PARTB.AMT;
            }
        }
    }
    public void R000_FEE_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFEEL);
        IBS.init(SCCGWA, BPCIFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPCIFHIS.INPUT.FUNC = '1';
        BPCIFHIS.INPUT.STR_AC_DT = WS_AC_DT;
        BPCIFHIS.INPUT.END_AC_DT = WS_AC_DT;
        BPCIFHIS.INPUT.JRNNO = WS_JRN_KEY;
        BPCIFHIS.INPUT.REC_LEN = 690;
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        WS_END_FLG = 'N';
        WS_I = 0;
        while (WS_END_FLG != 'Y') {
            BPCIFHIS.INPUT.FUNC = '2';
            BPCIFHIS.INPUT.REC_PT = BPRFHIST;
            S000_CALL_BPZIFHIS();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
                WS_END_FLG = 'Y';
            } else {
                if (BPRFHIST.REF_NO.equalsIgnoreCase(K_FEE_REF) 
                    && BPRFHIST.RLT_REF_NO.trim().length() == 0) {
                    R000_TRANS_DATA_TO_FEE();
                    if (pgmRtn) return;
                }
            }
        }
        BPCIFHIS.INPUT.FUNC = '3';
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        if (WS_I > 0) {
            R000_OUTPUT_FEE_DATA();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_TO_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFFHIS);
        WS_I += 1;
        BPCOFEEL.FEE_DATA.FEE_CNT = WS_I;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].CHG_FLG = BPRFFHIS.VAL.CHG_FLG;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].FEE_CODE = BPRFFHIS.KEY.FEE_CODE;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].FEE_CCY = BPRFFHIS.VAL.FEE_CCY;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].FEE_BAS = BPRFFHIS.VAL.FEE_BAS;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].FEE_AMT = BPRFFHIS.VAL.FEE_AMT;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].CHG_AC_TY = BPRFFHIS.VAL.CHG_AC_TY;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].CHG_AC = BPRFFHIS.VAL.CHG_AC;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].CHG_CCY = BPRFFHIS.VAL.CHG_CCY;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].CHG_BAS = BPRFFHIS.VAL.CHG_BAS;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].CHG_AMT = BPRFFHIS.VAL.CHG_AMT;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].ADJ_AMT = BPRFFHIS.VAL.ADJ_AMT;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].DER_AMT = BPRFFHIS.VAL.DER_AMT;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].RGN_NO = BPRFFHIS.KEY.RGN_NO;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].FREE_FMT = BPRFFHIS.KEY.FREE_FMT;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].TX_AC = BPRFFHIS.VAL.TX_AC;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].REF_NO = BPRFFHIS.VAL.REF_NO;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].PROD_CD = BPRFFHIS.VAL.TX_PROD;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].TX_CCY = BPRFFHIS.VAL.TX_CCY;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].TX_AMT = BPRFFHIS.VAL.TX_AMT;
        BPCOFEEL.FEE_DATA.FEE_INFO[WS_I-1].TX_CNT = BPRFFHIS.VAL.TX_CNT;
        CEP.TRC(SCCGWA, BPCOFEEL.FEE_DATA.FEE_CNT);
    }
    public void R000_OUTPUT_FEE_DATA() throws IOException,SQLException,Exception {
        if (BPCOFEEL.FEE_DATA.FEE_CNT > 0) {
            BPCOFEEL.FEE_DATA.SEND_FLG = '0';
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_3;
            SCCFMT.DATA_LEN = 10010;
            SCCFMT.DATA_PTR = BPCOFEEL;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_GET_VCH_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOVINQ);
        if (BPCSQTXH.FUNC == 'T' 
            || BPCSQTXH.FUNC == 'B') {
            BPCOVINQ.FUNC_CD = 'Q';
            BPCOVINQ.JRN_NO = WS_VCH_JRNNO;
            if (BPCSQTXH.DATA.AC_DT == 0) {
                BPCOVINQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                BPCOVINQ.AC_DATE = WS_AC_DT;
            }
            BPCOVINQ.VCH_PTR = BPRVCHO;
            IBS.init(SCCGWA, BPRVCHO.BASIC_AREA);
            BPRVCHO.BASIC_AREA.CNT = 0;
            VCH_AREA = new VCHO_VCH_AREA();
            BPRVCHO.VCH_AREA.add(VCH_AREA);
        }
        if (BPCSQTXH.FUNC == 'Q') {
            BPCOVINQ.JRN_NO = WS_VCH_JRNNO;
            BPCOVINQ.AC_DATE = WS_AC_DT;
            BPCOVINQ.FUNC_CD = 'P';
        }
        CEP.TRC(SCCGWA, BPCOVINQ.JRN_NO);
        CEP.TRC(SCCGWA, BPCOVINQ.AC_DATE);
        S000_CALL_BPZPVINQ();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_CHNL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_CHNL_INFO);
        WS_CHNL_INFO.WS_CHNL_NO = SCRJRN.CHNL;
        WS_CHNL_INFO.WS_REQ_SYSTEM = SCRJRN.REQ_SYSTEM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CHNL;
        SCCFMT.DATA_LEN = 10;
        SCCFMT.DATA_PTR = WS_CHNL_INFO;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_WRITE_TSQ_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 257;
        SCCMPAG.SCR_ROW_CNT = 57;
        SCCMPAG.SCR_COL_CNT = 200;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TSQ_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 257;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_INQ_TLR, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZCOUT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_OUTPUT, SCCCOUT);
        if (SCCCOUT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCCOUT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZPINP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPINP);
        IBS.init(SCCGWA, SCRINP);
        IBS.init(SCCGWA, SCRINPH);
        SCCPINP.FUNC = '1';
        if (WS_AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "AAA");
            WS_HIS_FLG = 'N';
            SCCPINP.HIS_FLAG = 'N';
            SCCPINP.DATA_LEN = 49;
            SCRINP.KEY.JRN_NO = WS_JRN_KEY;
            SCCPINP.DATA_PTR = SCRINP;
        } else {
            CEP.TRC(SCCGWA, "BBB");
            WS_HIS_FLG = 'Y';
            SCCPINP.HIS_FLAG = 'Y';
            SCCPINP.DATA_LEN = 57;
            SCRINPH.KEY.AC_DATE = WS_AC_DT;
            CEP.TRC(SCCGWA, WS_JRN_KEY);
            SCRINPH.KEY.JRN_NO = WS_JRN_KEY;
            SCCPINP.DATA_PTR = SCRINPH;
        }
        CEP.TRC(SCCGWA, SCCPINP);
        CEP.TRC(SCCGWA, SCRINP);
        CEP.TRC(SCCGWA, SCRINP.KEY.JRN_NO);
        IBS.CALLCPN(SCCGWA, K_CMP_INQ_INP, SCCPINP);
        CEP.TRC(SCCGWA, "AFTER-CALL-SCZPINP");
        CEP.TRC(SCCGWA, SCRINP.BLOB_DATA.trim().length());
        if (SCCPINP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPINP.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZPJRN() throws IOException,SQLException,Exception {
        SCCPJRN.DATA_PTR = SCRJRN;
        SCCPJRN.DATA_LEN = 687;
        CEP.TRC(SCCGWA, "CALL SCZPJRN");
        IBS.CALLCPN(SCCGWA, K_CMP_INQ_JRN, SCCPJRN);
        CEP.TRC(SCCGWA, SCCPJRN.RC);
        if (SCCPJRN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPJRN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPVINQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-VCH-INQ", BPCOVINQ);
        if (BPCOVINQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOVINQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SBUS_TRN, SCCSUBS);
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHIST", BPCIFHIS);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (BPCIFHIS.OUTPUT.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S010_CALL_SCZPJRN() throws IOException,SQLException,Exception {
        SCTJRNH_RD = new DBParm();
        SCTJRNH_RD.TableName = "SCTJRNH";
        SCTJRNH_RD.where = "AC_DATE = :SCRJRNH.AC_DATE "
            + "AND JRN_NO = :SCRJRNH.KEY.JRN_NO";
        SCTJRNH_RD.fst = true;
        IBS.READ(SCCGWA, SCRJRNH, this, SCTJRNH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STOP_FLG = 'Y';
            CEP.TRC(SCCGWA, "000SET000");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTJRNH";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRJRNH);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRJRN);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_ERR_INFO);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
