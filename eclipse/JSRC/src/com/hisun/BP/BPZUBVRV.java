package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUBVRV {
    int JIBS_tmp_int;
    char K_STS_NORMAL = '0';
    char K_STS_TOBE_DSTR = '1';
    char K_STS_DESTROYED = '2';
    char K_STS_CANCELED = '4';
    char K_STS_UNTREADED = '6';
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_MGM_TBVD = "BP-R-MGM-TBVD";
    String CPN_F_INQ_BV_ACT = "BP-F-INQ-BV-ACT";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_CKG_CLIB = "BP-P-CKG-CLIB";
    String CPN_P_TLAM = "BP-F-TLR-ACCU-MGM";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    String WS_BEG_NO = " ";
    long WS_COMP_BEGNO = 0;
    String WS_END_NO = " ";
    long WS_COMP_ENDNO = 0;
    int WS_TOTAL_NUM = 0;
    String WS_STORAGE = " ";
    String WS_TEMP_PLBOX_NO = " ";
    int WS_POS = 0;
    BPZUBVRV_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUBVRV_WS_EWA_AC_NO();
    char WS_VB_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPCLIB BPCPCLIB = new BPCPCLIB();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRTBVD BPRTEMP = new BPRTBVD();
    BPCCBVIO BPCCBVIO = new BPCCBVIO();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUBVRV BPCUBVRV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCUBVRV BPCUBVRV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUBVRV = BPCUBVRV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUBVRV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B020_CHK_BV_PARM();
        B030_CHK_BV_TLR();
        B050_PROCESS_TBVD_REC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B070_CHALK_IT_UP_CH();
            } else {
                B070_CHALK_IT_UP();
            }
        }
        B080_UPDATE_BPTVHPB();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCUBVRV.FUNC != '0' 
            && BPCUBVRV.FUNC != '1' 
            && BPCUBVRV.FUNC != '2' 
            && BPCUBVRV.FUNC != '3' 
            && BPCUBVRV.FUNC != '4')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERROR_FUNC;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCUBVRV.TYPE);
        if (BPCUBVRV.TYPE == '0'
            || BPCUBVRV.TYPE == '2'
            || BPCUBVRV.TYPE == '3'
            || BPCUBVRV.TYPE == '4') {
            if (BPCUBVRV.FUNC == '3') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANT_UNTREAD;
                S000_ERR_MSG_PROC();
            }
        } else if (BPCUBVRV.TYPE == '1') {
            if (BPCUBVRV.FUNC == '0' 
                || BPCUBVRV.FUNC == '2' 
                || BPCUBVRV.FUNC == '4') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANT_DES_CAN;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVTYPE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVRV.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCUBVRV.FUNC);
        if (BPCUBVRV.NUM == 0) {
            if (BPCUBVRV.FUNC == '2') {
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVRV.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVRV.VB_FLG == ' ') {
            BPCUBVRV.VB_FLG = '0';
        } else {
            if ((BPCUBVRV.VB_FLG != '0' 
                && BPCUBVRV.VB_FLG != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBFLG_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_CHK_BV_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCUBVRV.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPCUBVRV.HEAD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHK HEAD NO!");
            if (BPCUBVRV.HEAD_NO == null) BPCUBVRV.HEAD_NO = "";
            JIBS_tmp_int = BPCUBVRV.HEAD_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBVRV.HEAD_NO += " ";
            for (WS_I = 1; WS_I <= 10 
                && BPCUBVRV.HEAD_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCUBVRV.HEAD_NO);
            if (BPCFBVQU.TX_DATA.HEAD_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, "234");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_HEADNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVRV.BEG_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHK BEG NO!");
            if (BPCUBVRV.BEG_NO == null) BPCUBVRV.BEG_NO = "";
            JIBS_tmp_int = BPCUBVRV.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVRV.BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPCUBVRV.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVRV.END_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHK END NO!");
            if (BPCUBVRV.END_NO == null) BPCUBVRV.END_NO = "";
            JIBS_tmp_int = BPCUBVRV.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVRV.END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPCUBVRV.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPCUBVRV.HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                S000_ERR_MSG_PROC();
            }
            if (BPCUBVRV.BEG_NO.trim().length() > 0 
                || BPCUBVRV.END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') 
            && BPCUBVRV.FUNC != '2') {
            CEP.TRC(SCCGWA, "CHK BEGNO ENDNO NUM RELATIONSHIP!");
            if (BPCUBVRV.BEG_NO == null) BPCUBVRV.BEG_NO = "";
            JIBS_tmp_int = BPCUBVRV.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVRV.BEG_NO += " ";
            if (BPCUBVRV.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPCUBVRV.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPCUBVRV.END_NO == null) BPCUBVRV.END_NO = "";
            JIBS_tmp_int = BPCUBVRV.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVRV.END_NO += " ";
            if (BPCUBVRV.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPCUBVRV.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCUBVRV.BV_CODE;
            BPCSNOCK.BEG_NO = BPCUBVRV.BEG_NO;
            BPCSNOCK.END_NO = BPCUBVRV.END_NO;
            BPCSNOCK.NUM = BPCUBVRV.NUM;
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
        }
        if (BPCFBVQU.TX_DATA.TYPE == '1') {
            CEP.TRC(SCCGWA, "CHK BL INF!");
            if (BPCUBVRV.CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
            } else {
                if (!BPCUBVRV.CCY.equalsIgnoreCase(BPCFBVQU.TX_DATA.CCY)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCUBVRV.PVAL == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VAL_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCUBVRV.AMT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCUBVRV.PVAL);
            CEP.TRC(SCCGWA, BPCUBVRV.NUM);
            CEP.TRC(SCCGWA, BPCUBVRV.AMT);
            if (BPCUBVRV.AMT != BPCUBVRV.PVAL * BPCUBVRV.NUM) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_VAL_NUM;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPCUBVRV.PVAL = 0;
        }
    }
    public void B030_CHK_BV_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCUBVRV.TLR;
        S000_CALL_BPZFTLRQ();
        IBS.init(SCCGWA, BPCPCLIB);
        BPCPCLIB.TLR = BPCUBVRV.TLR;
        if (BPCUBVRV.VB_FLG == '0') {
            BPCPCLIB.VB_FLG = '3';
            WS_VB_FLG = '0';
        } else {
            BPCPCLIB.VB_FLG = BPCUBVRV.VB_FLG;
            WS_VB_FLG = '1';
        }
        S000_CALL_BPZPCLIB();
        if (BPCFBVQU.TX_DATA.TYPE == '0'
            || BPCFBVQU.TX_DATA.TYPE == '2'
            || BPCFBVQU.TX_DATA.TYPE == '3'
            || BPCFBVQU.TX_DATA.TYPE == '4') {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = BPCUBVRV.TLR;
            BPRVHPB.POLL_BOX_IND = BPCUBVRV.VB_FLG;
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            } else {
                if (WS_VB_FLG == '0') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVV_TLR;
                    S000_ERR_MSG_PROC();
                }
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPCRVHPB.INFO.FUNC = 'R';
            S000_CALL_BPZRVHPB();
        } else if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCUBVRV.VB_FLG == '0') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("0") 
                    && BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
                    S000_ERR_MSG_PROC();
                }
                if (BPCPCLIB.FLG == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CASH_BOX;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (BPCPCLIB.FLG == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CASH_VLT;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
    }
    public void B050_PROCESS_TBVD_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCBVIO);
        if (BPCUBVRV.FUNC != '2') {
            IBS.init(SCCGWA, BPCCBVIO);
            BPCCBVIO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
            BPCCBVIO.BV_CODE = BPCUBVRV.BV_CODE;
            BPCCBVIO.PVAL = BPCUBVRV.PVAL;
            BPCCBVIO.TYPE = BPCUBVRV.TYPE;
            BPCCBVIO.HEAD_NO = BPCUBVRV.HEAD_NO;
            BPCCBVIO.BEG_NO = BPCUBVRV.BEG_NO;
            BPCCBVIO.END_NO = BPCUBVRV.END_NO;
            BPCCBVIO.NUM = BPCUBVRV.NUM;
            BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
            BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
            BPCCBVIO.UPD_TLR = BPRTLT.KEY.TLR;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPCUBVRV.FUNC == '0') {
                BPCCBVIO.BV_STS = K_STS_NORMAL;
                R_DELETE_PROC();
                BPCCBVIO.BV_STS = K_STS_TOBE_DSTR;
                R_ADD_PROC();
            } else if (BPCUBVRV.FUNC == '1') {
                if (BPCUBVRV.TYPE != '1') {
                    BPCCBVIO.BV_STS = K_STS_TOBE_DSTR;
                    R_DELETE_PROC();
                } else {
                    BPCCBVIO.BV_STS = K_STS_NORMAL;
                    R_DELETE_PROC();
                }
            } else if (BPCUBVRV.FUNC == '2') {
                IBS.init(SCCGWA, BPRTBVD);
                BPRTBVD.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
                BPRTBVD.KEY.BV_CODE = BPCUBVRV.BV_CODE;
                BPRTBVD.KEY.STS = K_STS_TOBE_DSTR;
                IBS.init(SCCGWA, BPCRTBDB);
                BPCRTBDB.INFO.FUNC = '5';
                S000_CALL_BPZRTBDB();
                IBS.init(SCCGWA, BPCRTBDB);
                BPCRTBDB.INFO.FUNC = 'M';
                S000_CALL_BPZRTBDB();
                if (BPCRTBDB.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_REC_DESTROY;
                    S000_ERR_MSG_PROC();
                }
                BPCUBVRV.NUM = 0;
                for (WS_I = 1; WS_I <= 99 
                    && BPCRTBDB.RETURN_INFO != 'N'; WS_I += 1) {
                    IBS.init(SCCGWA, BPCCBVIO);
                    BPCUBVRV.DATA_INFO[WS_I-1].BV_CODE1 = BPRTBVD.KEY.BV_CODE;
                    BPCUBVRV.DATA_INFO[WS_I-1].HEAD_NO1 = BPRTBVD.KEY.HEAD_NO;
                    BPCUBVRV.DATA_INFO[WS_I-1].BEG_NO1 = BPRTBVD.BEG_NO;
                    BPCUBVRV.DATA_INFO[WS_I-1].END_NO1 = BPRTBVD.KEY.END_NO;
                    BPCUBVRV.DATA_INFO[WS_I-1].NUM1 = BPRTBVD.NUM;
                    BPCUBVRV.CNT = WS_I;
                    BPCCBVIO.BR = BPRTBVD.KEY.BR;
                    BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
                    BPCCBVIO.BV_CODE = BPRTBVD.KEY.BV_CODE;
                    BPCCBVIO.PVAL = BPRTBVD.KEY.VALUE;
                    BPCCBVIO.TYPE = BPRTBVD.TYPE;
                    BPCCBVIO.HEAD_NO = BPRTBVD.KEY.HEAD_NO;
                    BPCCBVIO.BEG_NO = BPRTBVD.BEG_NO;
                    BPCCBVIO.END_NO = BPRTBVD.KEY.END_NO;
                    BPCCBVIO.NUM = BPRTBVD.NUM;
                    BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
                    BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
                    BPCCBVIO.UPD_TLR = BPRTLT.KEY.TLR;
                    BPCCBVIO.BV_STS = K_STS_TOBE_DSTR;
                    R_DELETE_PROC();
                    CEP.TRC(SCCGWA, BPRTBVD.NUM);
                    BPCUBVRV.NUM += BPRTBVD.NUM;
                    IBS.init(SCCGWA, BPCRTBDB);
                    BPCRTBDB.INFO.FUNC = 'M';
                    S000_CALL_BPZRTBDB();
                }
                IBS.init(SCCGWA, BPCRTBDB);
                BPCRTBDB.INFO.FUNC = 'F';
                S000_CALL_BPZRTBDB();
            } else if (BPCUBVRV.FUNC == '3') {
                BPCCBVIO.BV_STS = K_STS_NORMAL;
                R_DELETE_PROC();
                BPCCBVIO.BV_STS = K_STS_UNTREADED;
                R_ADD_PROC();
            } else if (BPCUBVRV.FUNC == '4') {
                BPCCBVIO.BV_STS = K_STS_NORMAL;
                R_DELETE_PROC();
            } else {
            }
        } else {
            if (BPCUBVRV.FUNC == '0') {
                BPCCBVIO.BV_STS = K_STS_TOBE_DSTR;
                R_DELETE_PROC();
                BPCCBVIO.BV_STS = K_STS_NORMAL;
                R_ADD_PROC();
            } else if (BPCUBVRV.FUNC == '1') {
                if (BPCUBVRV.TYPE == '0') {
                    BPCCBVIO.BV_STS = K_STS_DESTROYED;
                    R_DELETE_PROC();
                    BPCCBVIO.BV_STS = K_STS_TOBE_DSTR;
                    R_ADD_PROC();
                } else {
                    BPCCBVIO.BV_STS = K_STS_DESTROYED;
                    R_DELETE_PROC();
                    BPCCBVIO.BV_STS = K_STS_NORMAL;
                    R_ADD_PROC();
                }
            } else if (BPCUBVRV.FUNC == '2') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEST_CANT_CANCEL;
                S000_ERR_MSG_PROC();
            } else if (BPCUBVRV.FUNC == '3') {
                BPCCBVIO.BV_STS = K_STS_UNTREADED;
                R_DELETE_PROC();
                BPCCBVIO.BV_STS = K_STS_NORMAL;
                R_ADD_PROC();
            } else if (BPCUBVRV.FUNC == '4') {
                BPCCBVIO.BV_STS = K_STS_CANCELED;
                R_DELETE_PROC();
                BPCCBVIO.BV_STS = K_STS_NORMAL;
                R_ADD_PROC();
            } else {
            }
        }
        if (BPCUBVRV.FUNC != '2') {
            R_CHK_BV_NUM();
        }
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP != '1') {
            if (BPCUBVRV.FUNC != '0') {
                B071_SET_EWA_BASIC_INF();
                B072_SET_EWA_EVENTS();
            }
        }
    }
    public void B070_CHALK_IT_UP_CH() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            if (BPCUBVRV.FUNC != '0') {
                B071_SET_EWA_BASIC_INF();
                B072_SET_EWA_EVENTS_CH();
            } else {
                B071_SET_EWA_BASIC_INF();
                B073_SET_EWA_EVENTS_DSTR_CN();
            }
        }
    }
    public void B071_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B072_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVZUBVRV";
        BPCPOEWA.DATA.BR_OLD = BPCFTLRQ.INFO.TLR_BR;
        BPCPOEWA.DATA.BR_NEW = BPCFTLRQ.INFO.TLR_BR;
        if (BPCFBVQU.TX_DATA.TYPE == '0') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBVRV.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBVRV.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBVRV.AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        BPCPOEWA.DATA.PORT = BPCUBVRV.BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBVRV.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B072_SET_EWA_EVENTS_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        if (BPCUBVRV.FUNC == '1' 
            || BPCUBVRV.FUNC == '2') {
            BPCPOEWA.DATA.EVENT_CODE = "BVZUCR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCFBVQU.TX_DATA.TYPE != '1') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBVRV.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBVRV.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBVRV.AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        BPCPOEWA.DATA.PORT = BPCUBVRV.BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBVRV.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B073_SET_EWA_EVENTS_DSTR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCFBVQU.TX_DATA.TYPE != '1') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBVRV.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBVRV.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBVRV.AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        BPCPOEWA.DATA.PORT = BPCUBVRV.BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBVRV.BV_CODE;
        S000_CALL_BPZPOEWA();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVZUDR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCFBVQU.TX_DATA.TYPE != '1') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBVRV.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBVRV.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBVRV.AMT;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.VALUE_DATE);
        BPCPOEWA.DATA.PORT = BPCUBVRV.BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBVRV.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        CEP.TRC(SCCGWA, BPCPOEWA.RC);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B080_UPDATE_BPTVHPB() throws IOException,SQLException,Exception {
        BPRVHPB.BV_CHK_FLG = 'N';
        BPRVHPB.BL_CHK_FLG = 'N';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = BPRTLT.KEY.TLR;
        BPCRVHPB.INFO.FUNC = 'U';
        S000_CALL_BPZRVHPB();
    }
    public void B090_UPDATE_TLR_ACCU() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            IBS.init(SCCGWA, BPCFTLAM);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                BPCFTLAM.OP_CODE = 'D';
            } else {
                BPCFTLAM.OP_CODE = 'A';
            }
            BPCFTLAM.ACCU_TYP = "14";
            BPCFTLAM.TLR = BPCUBVRV.TLR;
            CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
            BPCFTLAM.CCY = BPCRBANK.LOC_CCY1;
            if (BPCFBVQU.TX_DATA.TYPE == '0') {
                BPCFTLAM.AMT = (double)BPCUBVRV.NUM;
            } else {
                BPCFTLAM.AMT = BPCUBVRV.AMT;
            }
            S000_CALL_BPZFTLAM();
        }
    }
    public void B100_ADD_BUSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBVRV.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCUBVRV.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBVRV.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBVRV.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = BPCUBVRV.TLR;
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '0';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void R_CHK_BV_NUM() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCUBVRV.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBVRV.PVAL;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'R';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if ((BPCUBVRV.VB_FLG == '1' 
                    && BPRTBVD.NUM <= BPCFBVQU.TX_DATA.V_LMT) 
                    || (BPCUBVRV.VB_FLG == '0' 
                    && BPRTBVD.NUM <= BPCFBVQU.TX_DATA.B_LMT)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            IBS.init(SCCGWA, BPRTBVD);
            WS_TOTAL_NUM = 0;
            BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCUBVRV.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBVRV.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCUBVRV.HEAD_NO;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '2';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            }
            while (BPCRTBDB.RETURN_INFO != 'N') {
                WS_TOTAL_NUM += BPRTBVD.NUM;
                IBS.init(SCCGWA, BPCRTBDB);
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
            }
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.B_LMT);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.B_LMT);
            CEP.TRC(SCCGWA, WS_TOTAL_NUM);
            if ((BPCUBVRV.VB_FLG == '1' 
                && (WS_TOTAL_NUM <= BPCFBVQU.TX_DATA.V_LMT)) 
                || (BPCUBVRV.VB_FLG == '0' 
                && (WS_TOTAL_NUM <= BPCFBVQU.TX_DATA.B_LMT))) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                CEP.TRC(SCCGWA, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBVD() throws IOException,SQLException,Exception {
        BPCRTBVD.INFO.POINTER = BPRTBVD;
        BPCRTBVD.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TBVD, BPCRTBVD);
        if (BPCRTBVD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBVD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CKG_CLIB, BPCPCLIB);
        if (BPCPCLIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCLIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_TLAM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void R_DELETE_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            R_DELETE_NONUMBER_PROC();
        } else if (BPCCBVIO.CTL_FLG == '1'
            || BPCCBVIO.CTL_FLG == '2') {
            WS_POS = 20;
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, BPCCBVIO.BVNO_LEN);
            WS_POS = WS_POS - BPCCBVIO.BVNO_LEN + 1;
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
            BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
            BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
            BPRTBVD.NUM = BPCCBVIO.NUM;
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, "140133");
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
            CEP.TRC(SCCGWA, BPCCBVIO.COMP_BEGNO);
            CEP.TRC(SCCGWA, WS_POS);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '3';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "TEST002");
            CEP.TRC(SCCGWA, BPRTBVD.VIL_TYP);
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CEP.TRC(SCCGWA, BPCCBVIO.VILCTL);
            if (!BPCCBVIO.VIL_TYP.equalsIgnoreCase(BPRTBVD.VIL_TYP) 
                && BPCCBVIO.VILCTL == 'Y' 
                && BPRTBVD.VIL_TYP.trim().length() > 0 
                && !BPRTBVD.VIL_TYP.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VIL_TYP_NOTSAME);
            }
            R_DELETE_COMMON_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_DELETE_NO_HEAD_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            R_DELETE_NONUMBER_PROC();
        } else if (BPCCBVIO.CTL_FLG == '1'
            || BPCCBVIO.CTL_FLG == '2') {
            WS_POS = 20;
            WS_POS = WS_POS - BPCCBVIO.BVNO_LEN + 1;
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
            BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
            BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '8';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPCCBVIO.HEAD_NO = BPRTBVD.KEY.HEAD_NO;
            R_DELETE_COMMON_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_DELETE_COMMON_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.BEG_NO.equalsIgnoreCase(BPRTBVD.BEG_NO) 
                && BPCCBVIO.END_NO.equalsIgnoreCase(BPRTBVD.KEY.END_NO)) {
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            CEP.TRC(SCCGWA, BPCRTBDB.INFO.FUNC);
            S000_CALL_BPZRTBDB();
        } else if (BPCCBVIO.BEG_NO.equalsIgnoreCase(BPRTBVD.BEG_NO) 
                && BPCCBVIO.END_NO.compareTo(BPRTBVD.KEY.END_NO) < 0) {
            if (BPCCBVIO.END_NO == null) BPCCBVIO.END_NO = "";
            JIBS_tmp_int = BPCCBVIO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.END_NO += " ";
            if (BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.END_NO;
            BPCSNOCK.FUNC = '4';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.NEXT_NO.trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCSNOCK.NEXT_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_ENDNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.BEG_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, WS_STORAGE);
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, BPCCBVIO.NUM);
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'W';
            S000_CALL_BPZRTBDB();
        } else if (BPCCBVIO.BEG_NO.compareTo(BPRTBVD.BEG_NO) > 0 
                && BPCCBVIO.END_NO.equalsIgnoreCase(BPRTBVD.KEY.END_NO)) {
            IBS.CLONE(SCCGWA, BPRTBVD, BPRTEMP);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            S000_CALL_BPZRTBDB();
            IBS.CLONE(SCCGWA, BPRTEMP, BPRTBVD);
            if (BPCCBVIO.BEG_NO == null) BPCCBVIO.BEG_NO = "";
            JIBS_tmp_int = BPCCBVIO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.BEG_NO += " ";
            if (BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.BEG_NO;
            BPCSNOCK.FUNC = '5';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.PRE_NO.trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCSNOCK.PRE_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_BEGNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
        } else if (BPCCBVIO.BEG_NO.compareTo(BPRTBVD.BEG_NO) > 0 
                && BPCCBVIO.END_NO.compareTo(BPRTBVD.KEY.END_NO) < 0) {
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.CLONE(SCCGWA, BPRTBVD, BPRTEMP);
            if (BPRTBVD.KEY.END_NO == null) BPRTBVD.KEY.END_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.KEY.END_NO += " ";
            if (BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            if (BPCCBVIO.END_NO == null) BPCCBVIO.END_NO = "";
            JIBS_tmp_int = BPCCBVIO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.END_NO += " ";
            if (BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.END_NO;
            BPCSNOCK.FUNC = '4';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.NEXT_NO.trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCSNOCK.NEXT_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_BEGNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.BEG_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'W';
            S000_CALL_BPZRTBDB();
            IBS.CLONE(SCCGWA, BPRTEMP, BPRTBVD);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.BEG_NO;
            BPCSNOCK.FUNC = '5';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.PRE_NO.trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCSNOCK.PRE_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_ENDNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
        } else {
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
    }
    public void R_DELETE_NONUMBER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = BPCCBVIO.BR;
        BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
        BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        IBS.init(SCCGWA, BPCRTBVD);
        BPCRTBVD.INFO.FUNC = 'R';
        S000_CALL_BPZRTBVD();
        if (BPCRTBVD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPRTBVD.NUM = BPRTBVD.NUM - BPCCBVIO.NUM;
        if (BPRTBVD.NUM > 0) {
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'U';
            S000_CALL_BPZRTBVD();
        } else if (BPRTBVD.NUM == 0) {
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'D';
            S000_CALL_BPZRTBVD();
        } else if (BPRTBVD.NUM < 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_NOT_SUFFICIENT;
            S000_ERR_MSG_PROC();
        } else {
        }
    }
    public void R_ADD_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.VIL_TYP = BPCCBVIO.VIL_TYP;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'R';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'N') {
                BPRTBVD.TYPE = BPCCBVIO.TYPE;
                BPRTBVD.NUM = BPCCBVIO.NUM;
                BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
                IBS.init(SCCGWA, BPCRTBVD);
                BPCRTBVD.INFO.FUNC = 'A';
                S000_CALL_BPZRTBVD();
            } else {
                BPRTBVD.NUM = BPRTBVD.NUM + BPCCBVIO.NUM;
                BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
                IBS.init(SCCGWA, BPCRTBVD);
                BPCRTBVD.INFO.FUNC = 'U';
                S000_CALL_BPZRTBVD();
            }
        } else if (BPCCBVIO.CTL_FLG == '1'
            || BPCCBVIO.CTL_FLG == '2') {
            IBS.init(SCCGWA, BPRTBVD);
            WS_POS = 20;
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, BPCCBVIO.BVNO_LEN);
            WS_POS = WS_POS - BPCCBVIO.BVNO_LEN + 1;
            if (BPCCBVIO.BEG_NO == null) BPCCBVIO.BEG_NO = "";
            JIBS_tmp_int = BPCCBVIO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.BEG_NO += " ";
            if (BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN));
            if (BPCCBVIO.END_NO == null) BPCCBVIO.END_NO = "";
            JIBS_tmp_int = BPCCBVIO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.END_NO += " ";
            if (BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            R_FIND_RESEMBLE_REC();
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
            BPRTBVD.TYPE = BPCCBVIO.TYPE;
            BPRTBVD.NOW_NO = " ";
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            BPRTBVD.VIL_TYP = BPCCBVIO.VIL_TYP;
            CEP.TRC(SCCGWA, BPRTBVD.VIL_TYP);
            if (BPCCBVIO.FORWARD_FLG == 'Y'
                && BPCCBVIO.BACKWARD_FLG == 'Y') {
                BPRTBVD.KEY.END_NO = BPCCBVIO.COMBINE_ENDNO_X;
                BPRTBVD.BEG_NO = BPCCBVIO.COMBINE_BEGNO_X;
                CEP.TRC(SCCGWA, "1");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (BPCCBVIO.FORWARD_FLG == 'Y'
                && BPCCBVIO.BACKWARD_FLG == 'N') {
                BPRTBVD.KEY.END_NO = BPCCBVIO.COMBINE_ENDNO_X;
                BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
                CEP.TRC(SCCGWA, "2");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (BPCCBVIO.FORWARD_FLG == 'N'
                && BPCCBVIO.BACKWARD_FLG == 'Y') {
                BPRTBVD.BEG_NO = BPCCBVIO.COMBINE_BEGNO_X;
                BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
                CEP.TRC(SCCGWA, "3");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (BPCCBVIO.FORWARD_FLG == 'N'
                && BPCCBVIO.BACKWARD_FLG == 'N') {
                BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
                BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
                CEP.TRC(SCCGWA, "4");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else {
            }
            if (BPRTBVD.BEG_NO == null) BPRTBVD.BEG_NO = "";
            JIBS_tmp_int = BPRTBVD.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.BEG_NO += " ";
            if (BPRTBVD.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPRTBVD.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN));
            if (BPRTBVD.KEY.END_NO == null) BPRTBVD.KEY.END_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.KEY.END_NO += " ";
            if (BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NTOT_DUPLICATE;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_FIND_RESEMBLE_REC() throws IOException,SQLException,Exception {
        BPRTBVD.KEY.BR = BPCCBVIO.BR;
        BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
        BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
        IBS.init(SCCGWA, BPCSNOCK);
        BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
        BPCSNOCK.BV_NO = BPCCBVIO.BEG_NO;
        BPCSNOCK.FUNC = '5';
        S000_CALL_BPZSNOCK();
        if (BPCSNOCK.PRE_NO.trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
        else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCSNOCK.PRE_NO);
        WS_STORAGE = "" + BPCCBVIO.COMP_BEGNO;
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
        CEP.TRC(SCCGWA, WS_STORAGE);
        CEP.TRC(SCCGWA, WS_POS);
        CEP.TRC(SCCGWA, BPCCBVIO.BVNO_LEN);
        if (WS_STORAGE == null) WS_STORAGE = "";
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
        BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
        BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
        IBS.init(SCCGWA, BPCRTBVD);
        BPCRTBVD.INFO.FUNC = 'R';
        S000_CALL_BPZRTBVD();
        if (BPCRTBVD.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "STEP1");
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            BPCCBVIO.BACKWARD_FLG = 'N';
        } else {
            BPCCBVIO.BACKWARD_FLG = 'Y';
            BPCCBVIO.COMBINE_BEGNO_X = BPRTBVD.BEG_NO;
            CEP.TRC(SCCGWA, BPCCBVIO.COMBINE_BEGNO_X);
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'D';
            S000_CALL_BPZRTBVD();
        }
        CEP.TRC(SCCGWA, "STEP2");
        CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
        BPRTBVD.KEY.BR = BPCCBVIO.BR;
        BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
        BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
        IBS.init(SCCGWA, BPCSNOCK);
        BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
        BPCSNOCK.BV_NO = BPCCBVIO.END_NO;
        BPCSNOCK.FUNC = '4';
        S000_CALL_BPZSNOCK();
        if (BPCSNOCK.NEXT_NO.trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
        else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCSNOCK.NEXT_NO);
        WS_STORAGE = "" + BPCCBVIO.COMP_ENDNO;
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
        if (WS_STORAGE == null) WS_STORAGE = "";
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
        BPRTBVD.BEG_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
        BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '4';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "STEP3");
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        if (BPCRTBDB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "STEP4");
            BPCCBVIO.FORWARD_FLG = 'N';
        } else {
            BPCCBVIO.FORWARD_FLG = 'Y';
            BPCCBVIO.COMBINE_ENDNO_X = BPRTBVD.KEY.END_NO;
            CEP.TRC(SCCGWA, BPCCBVIO.COMBINE_ENDNO_X);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            S000_CALL_BPZRTBDB();
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "STEP5");
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
