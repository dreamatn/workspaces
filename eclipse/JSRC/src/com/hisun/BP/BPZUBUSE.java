package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUBUSE {
    DBParm BPTINVT_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_STS_NORMAL = '0';
    char K_STS_PAYOUT = '3';
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_R_MGM_TBV = "BP-R-MGM-TBV";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_MGM_TBVD = "BP-R-MGM-TBVD";
    String CPN_F_INQ_BV_ACT = "BP-F-INQ-BV-ACT";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_CKG_CLIB = "BP-P-CKG-CLIB";
    String CPN_P_TLAM = "BP-F-TLR-ACCU-MGM";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_U_BVUSE = "BP-U-TLR-BV-USE     ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_R_MGM_VCOR = "BP-R-MGM-VCOR";
    String CPN_F_BVLT_CHK = "BP-F-BVLT-CHK       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String BP_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    String WS_BEG_NO = " ";
    long WS_COMP_BEGNO = 0;
    String WS_END_NO = " ";
    long WS_COMP_ENDNO = 0;
    short WS_TOTAL_NUM = 0;
    String WS_STORAGE = " ";
    String WS_TEMP_PLBOX_NO = " ";
    int WS_CNT = 0;
    int WS_POS = 0;
    String WS_HEAD_NO = " ";
    BPZUBUSE_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUBUSE_WS_EWA_AC_NO();
    int WS_TR_BRANCH = 0;
    char WS_INVT_FLG = ' ';
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
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRVCOR BPRVCOR = new BPRVCOR();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRINVT BPRINVT = new BPRINVT();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCRVCOR BPCRVCOR = new BPCRVCOR();
    BPCCBVIO BPCCBVIO = new BPCCBVIO();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFBVF BPCFBVF = new BPCFBVF();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCFLTCK BPCFLTCK = new BPCFLTCK();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUBUSE BPCUBUSE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCUBUSE BPCUBUSE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUBUSE = BPCUBUSE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUBUSE return!");
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
        CEP.TRC(SCCGWA, BPCUBUSE.TLR);
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.TYPE);
        CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHK_INPUT_CH();
        } else {
            B010_CHK_INPUT();
        }
        B020_CHK_BV_PARM();
        B030_CHK_BV_TLR();
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.CUT_FLG);
        CEP.TRC(SCCGWA, BPCUBUSE.EC_IND);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.AC_TYP);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && (BPCUBUSE.EC_IND == '1' 
            || BPCFBVQU.TX_DATA.CUT_FLG == '1')) {
            B045_CREATE_VCH_RECORD();
        } else {
            B040_CHK_PROC_O();
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B050_PROCESS_MOV_OUT_CH();
            } else {
                B050_PROCESS_MOV_OUT();
            }
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B060_BUSE_HISTORY_PROC_CH();
            } else {
                B060_BUSE_HISTORY_PROC();
            }
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B070_CHALK_IT_UP_CN();
            } else {
                B070_CHALK_IT_UP();
            }
            B080_UPDATE_BPTTVHPB();
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUBUSE.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBUSE.NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBUSE.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        if (BPCUBUSE.VB_FLG == ' ') {
            BPCUBUSE.VB_FLG = '0';
        } else {
            if ((BPCUBUSE.VB_FLG != '0' 
                && BPCUBUSE.VB_FLG != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBFLG_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBUSE.EC_IND == ' ') {
            BPCUBUSE.EC_IND = '0';
        } else {
            if ((BPCUBUSE.EC_IND != '0' 
                && BPCUBUSE.EC_IND != '1' 
                && BPCUBUSE.EC_IND != '2')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EC_IND_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCUBUSE.TYPE != '0' 
            && BPCUBUSE.TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVTYPE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBUSE.COUNT_MTH == ' ') {
            BPCUBUSE.COUNT_MTH = '1';
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCUBUSE.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCIFHIS);
            IBS.init(SCCGWA, BPRFHIST);
            BPCIFHIS.INPUT.FUNC = '5';
            BPRFHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRFHIST.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            BPRFHIST.KEY.JRN_SEQ = 1;
            BPCIFHIS.INPUT.REC_PT = BPRFHIST;
            BPCIFHIS.INPUT.REC_LEN = 690;
            S000_CALL_BPZIFHIS();
            CEP.TRC(SCCGWA, BPRFHIST.TX_BR);
            CEP.TRC(SCCGWA, BPRFHIST.TX_TLR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            if (BPRFHIST.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                S000_ERR_MSG_PROC();
            }
            if (!BPRFHIST.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_CHK_INPUT_CH() throws IOException,SQLException,Exception {
        if (BPCUBUSE.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBUSE.NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBUSE.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        if (BPCUBUSE.VB_FLG == ' ') {
            BPCUBUSE.VB_FLG = '0';
        } else {
            if ((BPCUBUSE.VB_FLG != '0' 
                && BPCUBUSE.VB_FLG != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBFLG_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBUSE.EC_IND == ' ') {
            BPCUBUSE.EC_IND = '0';
        } else {
            if ((BPCUBUSE.EC_IND != '0' 
                && BPCUBUSE.EC_IND != '1' 
                && BPCUBUSE.EC_IND != '2')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EC_IND_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCUBUSE.TYPE != '0' 
            && BPCUBUSE.TYPE != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVTYPE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBUSE.COUNT_MTH == ' ') {
            BPCUBUSE.COUNT_MTH = '1';
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, "NCB032801");
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.INT_BR_FLG);
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && BPCPQORG.INT_BR_FLG == 'Y') {
            IBS.init(SCCGWA, BPCPQORR);
            BPCPQORR.TYP = "12";
            BPCPQORR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORR();
            WS_TR_BRANCH = BPCPQORR.REL_BR;
        } else {
            WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CEP.TRC(SCCGWA, "NCB0531001");
        CEP.TRC(SCCGWA, WS_TR_BRANCH);
        IBS.init(SCCGWA, BPRINVT);
        BPRINVT.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.TLR_D = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "NCB0821001");
        CEP.TRC(SCCGWA, BPRINVT.KEY.DATE);
        CEP.TRC(SCCGWA, BPRINVT.TLR_D);
        T000_READ_BPTINVT_FIRST();
        CEP.TRC(SCCGWA, WS_INVT_FLG);
        if (WS_INVT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "NCB0821002");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BOX_CHK_INVT;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_BPTINVT_FIRST() throws IOException,SQLException,Exception {
        BPTINVT_RD = new DBParm();
        BPTINVT_RD.TableName = "BPTINVT";
        BPTINVT_RD.where = "'DATE` = :INVT_DATE AND  TLR_D = :INVT_TLR_D AND  CB_TYP = `1' "
            + "AND INVNTYP = '2' "
            + "AND VB_FLG = '0'";
        BPTINVT_RD.fst = true;
        BPTINVT_RD.order = "JRNNO";
        IBS.READ(SCCGWA, BPRINVT, this, BPTINVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_INVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_INVT_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTINVT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void B020_CHK_BV_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCUBUSE.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && BPCFBVQU.TX_DATA.CUT_FLG == '0') {
            IBS.init(SCCGWA, BPRBUSE);
            IBS.init(SCCGWA, BPCRBUSE);
            BPRBUSE.KEY.BR = WS_TR_BRANCH;
            BPRBUSE.KEY.BV_CODE = BPCUBUSE.BV_CODE;
            BPRBUSE.KEY.HEAD_NO = BPCUBUSE.HEAD_NO;
            BPRBUSE.KEY.BEG_NO = BPCUBUSE.BEG_NO;
            BPRBUSE.KEY.END_NO = BPCUBUSE.END_NO;
            BPRBUSE.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            CEP.TRC(SCCGWA, BPRBUSE.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRBUSE.KEY.HEAD_NO);
            CEP.TRC(SCCGWA, BPRBUSE.KEY.BEG_NO);
            CEP.TRC(SCCGWA, BPRBUSE.KEY.END_NO);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
            CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
            BPCRBUSE.INFO.FUNC = 'Q';
            S000_CALL_BPZRBUSE();
            if (BPCRBUSE.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BUSE_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPRBUSE.TX_BR);
            CEP.TRC(SCCGWA, BPRBUSE.TX_TLR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, WS_TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            if (BPRBUSE.TX_BR != WS_TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPCUBUSE.TYPE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.TYPE);
        if (BPCUBUSE.HEAD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CHK HEAD NO!");
            CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
            if (BPCUBUSE.HEAD_NO == null) BPCUBUSE.HEAD_NO = "";
            JIBS_tmp_int = BPCUBUSE.HEAD_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBUSE.HEAD_NO += " ";
            for (WS_I = 1; WS_I <= 10 
                && BPCUBUSE.HEAD_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
            if (BPCFBVQU.TX_DATA.HEAD_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_HEADNO_LEN;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
        }
        if (BPCUBUSE.BEG_NO.trim().length() > 0) {
            if (BPCUBUSE.BEG_NO == null) BPCUBUSE.BEG_NO = "";
            JIBS_tmp_int = BPCUBUSE.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBUSE.BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPCUBUSE.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
            CEP.TRC(SCCGWA, WS_I);
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBUSE.END_NO.trim().length() > 0) {
            if (BPCUBUSE.END_NO == null) BPCUBUSE.END_NO = "";
            JIBS_tmp_int = BPCUBUSE.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBUSE.END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPCUBUSE.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPCUBUSE.BEG_NO.trim().length() > 0 
                || BPCUBUSE.END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPCUBUSE.BEG_NO == null) BPCUBUSE.BEG_NO = "";
            JIBS_tmp_int = BPCUBUSE.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBUSE.BEG_NO += " ";
            if (BPCUBUSE.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPCUBUSE.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPCUBUSE.END_NO == null) BPCUBUSE.END_NO = "";
            JIBS_tmp_int = BPCUBUSE.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBUSE.END_NO += " ";
            if (BPCUBUSE.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPCUBUSE.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCUBUSE.BV_CODE;
            BPCSNOCK.BEG_NO = BPCUBUSE.BEG_NO;
            BPCSNOCK.END_NO = BPCUBUSE.END_NO;
            BPCSNOCK.NUM = BPCUBUSE.NUM;
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
        }
        if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCUBUSE.CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
            } else {
                if (!BPCUBUSE.CCY.equalsIgnoreCase(BPCFBVQU.TX_DATA.CCY)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCUBUSE.PVAL == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VAL_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCUBUSE.AMT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCUBUSE.AMT != BPCUBUSE.PVAL * BPCUBUSE.NUM) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_VAL_NUM;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPCUBUSE.PVAL = 0;
        }
    }
    public void B030_CHK_BV_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCUBUSE.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFBVQU.TX_DATA.TYPE == '0'
            || BPCFBVQU.TX_DATA.TYPE == '2'
            || BPCFBVQU.TX_DATA.TYPE == '3'
            || BPCFBVQU.TX_DATA.TYPE == '4') {
            CEP.TRC(SCCGWA, "AAAAAAAAAAA");
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = WS_TR_BRANCH;
            BPRVHPB.CUR_TLR = BPCUBUSE.TLR;
            BPRVHPB.POLL_BOX_IND = BPCUBUSE.VB_FLG;
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'L';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.BR);
            CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPCRVHPB.INFO.FUNC = 'R';
            S000_CALL_BPZRVHPB();
        } else if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCUBUSE.VB_FLG == '0') {
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
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                R_CHK_USE_METHOD_CH();
            } else {
                R_CHK_USE_METHOD();
            }
        }
    }
    public void B040_CHK_PROC_O() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.OUT_FLG);
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if ((BPCFBVQU.TX_DATA.OUT_FLG == '0' 
                && BPCUBUSE.VB_FLG == '1') 
                || (BPCFBVQU.TX_DATA.OUT_FLG == '1' 
                && BPCUBUSE.VB_FLG == '0')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVOU_MTH;
                S000_ERR_MSG_PROC();
            }
            if (BPCFBVQU.TX_DATA.USE_MODE == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_MANUAL;
                S000_ERR_MSG_PROC();
            }
            if (BPCFBVQU.TX_DATA.USE_CTL == '1') {
                R_CHK_BV_LEAST_NO();
            }
        }
    }
    public void B045_CREATE_VCH_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRVCOR);
        IBS.init(SCCGWA, BPRVCOR);
        BPCRVCOR.INFO.FUNC = 'A';
        BPRVCOR.KEY.DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRVCOR.KEY.JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPRVCOR.DC_FLG = 'D';
        BPRVCOR.VB_FLG = BPCUBUSE.VB_FLG;
        BPRVCOR.STS = '0';
        BPRVCOR.BV_CODE = BPCUBUSE.BV_CODE;
        BPRVCOR.VALUE = BPCUBUSE.PVAL;
        if (BPCUBUSE.CCY.trim().length() == 0) {
            BPRVCOR.CCY = BPCRBANK.LOC_CCY1;
        } else {
            BPRVCOR.CCY = BPCUBUSE.CCY;
        }
        BPRVCOR.NUM = BPCUBUSE.NUM;
        BPRVCOR.BEG_NO = BPCUBUSE.BEG_NO;
        BPRVCOR.END_NO = BPCUBUSE.END_NO;
        BPRVCOR.TL_ID = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRVCOR.TR_BRANCH = WS_TR_BRANCH;
    }
    public void B050_PROCESS_MOV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCBVIO);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPCCBVIO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
            BPCCBVIO.VB_FLG = BPCUBUSE.VB_FLG;
            BPCCBVIO.BV_CODE = BPCUBUSE.BV_CODE;
            BPCCBVIO.PVAL = BPCUBUSE.PVAL;
            BPCCBVIO.TYPE = BPCUBUSE.TYPE;
            BPCCBVIO.BEG_NO = BPCUBUSE.BEG_NO;
            BPCCBVIO.END_NO = BPCUBUSE.END_NO;
            BPCCBVIO.NUM = BPCUBUSE.NUM;
            BPCCBVIO.BV_STS = K_STS_NORMAL;
            BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
            BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
            BPCCBVIO.UPD_TLR = BPRTLT.KEY.TLR;
            R_DELETE_NO_HEAD_PROC();
            WS_HEAD_NO = BPCCBVIO.HEAD_NO;
            BPCCBVIO.BV_STS = K_STS_PAYOUT;
            R_ADD_PROC();
        } else {
            BPCCBVIO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
            BPCCBVIO.BV_CODE = BPCUBUSE.BV_CODE;
            BPCCBVIO.PVAL = BPCUBUSE.PVAL;
            BPCCBVIO.TYPE = BPCUBUSE.TYPE;
            BPCCBVIO.BEG_NO = BPCUBUSE.BEG_NO;
            BPCCBVIO.END_NO = BPCUBUSE.END_NO;
            BPCCBVIO.NUM = BPCUBUSE.NUM;
            BPCCBVIO.BV_STS = K_STS_PAYOUT;
            BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
            BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
            BPCCBVIO.UPD_TLR = BPRTLT.KEY.TLR;
            R_DELETE_NO_HEAD_PROC();
            WS_HEAD_NO = BPCCBVIO.HEAD_NO;
            BPCCBVIO.BV_STS = K_STS_NORMAL;
            R_ADD_PROC();
        }
        R_CHK_BV_NUM();
    }
    public void B050_PROCESS_MOV_OUT_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCBVIO);
        BPCCBVIO.BR = WS_TR_BRANCH;
        BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
        BPCCBVIO.VB_FLG = BPCUBUSE.VB_FLG;
        BPCCBVIO.BV_CODE = BPCUBUSE.BV_CODE;
        BPCCBVIO.PVAL = BPCUBUSE.PVAL;
        BPCCBVIO.TYPE = BPCUBUSE.TYPE;
        BPCCBVIO.HEAD_NO = BPCUBUSE.HEAD_NO;
        BPCCBVIO.BEG_NO = BPCUBUSE.BEG_NO;
        BPCCBVIO.END_NO = BPCUBUSE.END_NO;
        BPCCBVIO.NUM = BPCUBUSE.NUM;
        BPCCBVIO.BV_STS = K_STS_NORMAL;
        BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
        BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
        BPCCBVIO.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "NCB1205001");
        CEP.TRC(SCCGWA, BPCCBVIO.BR);
        CEP.TRC(SCCGWA, BPCCBVIO.PLBOX_NO);
        CEP.TRC(SCCGWA, BPCCBVIO.VB_FLG);
        CEP.TRC(SCCGWA, BPCCBVIO.BV_CODE);
        CEP.TRC(SCCGWA, BPCCBVIO.PVAL);
        CEP.TRC(SCCGWA, BPCCBVIO.TYPE);
        CEP.TRC(SCCGWA, BPCCBVIO.HEAD_NO);
        CEP.TRC(SCCGWA, BPCCBVIO.BEG_NO);
        CEP.TRC(SCCGWA, BPCCBVIO.END_NO);
        CEP.TRC(SCCGWA, BPCCBVIO.NUM);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_DELETE_PROC();
        } else {
            R_ADD_PROC();
        }
    }
    public void B010_CHK_TLR_BV_HEAD_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = WS_TR_BRANCH;
        BPRTBVD.KEY.BV_CODE = BPCUBUSE.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCUBUSE.PVAL;
        BPRTBVD.BEG_NO = BPCUBUSE.BEG_NO;
        BPRTBVD.KEY.END_NO = BPCUBUSE.END_NO;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.STS = K_STS_NORMAL;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
        CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '8';
        S000_CALL_BPZRTBDB();
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCRTBDB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "13191319131913191319");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            CEP.TRC(SCCGWA, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.USE_CTL);
        if (BPCFBVQU.TX_DATA.USE_CTL == '1') {
            if (!BPRTBVD.BEG_NO.equalsIgnoreCase(BPCUBUSE.BEG_NO)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LEAST_NO_SEG;
                S000_ERR_MSG_PROC();
            }
        }
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        BPCUBUSE.HEAD_NO = BPRTBVD.KEY.HEAD_NO;
        CEP.TRC(SCCGWA, "NCB1204001");
        CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
    }
    public void R_CHK_BV_NUM_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFLTCK);
        BPCFLTCK.TX_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFLTCK.TX_DATA.TL_ID = BPCUBUSE.TLR;
        BPCFLTCK.TX_DATA.BV_CODE = BPCUBUSE.BV_CODE;
        BPCFLTCK.TX_DATA.VB_FLG = WS_TEMP_PLBOX_NO.charAt(0);
        BPCFLTCK.TX_DATA.PVAL = BPCUBUSE.PVAL;
        BPCFLTCK.TX_DATA.HEAD_NO = BPCUBUSE.HEAD_NO;
        BPCFLTCK.TX_DATA.STS = K_STS_NORMAL;
        S000_CALL_BPZFLTCK();
    }
    public void B060_BUSE_HISTORY_PROC() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.TYPE == '0' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B060_01_ADD_BUSE_PROCESS();
            } else {
                B060_02_ADD_BUSE_PROC_CANCEL();
            }
        }
    }
    public void B060_BUSE_HISTORY_PROC_CH() throws IOException,SQLException,Exception {
        if ((BPCFBVQU.TX_DATA.TYPE != '1') 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B060_01_ADD_BUSE_PROCESS_CH();
            } else {
                B060_02_ADD_BUSE_PROC_CAN_CH();
            }
        }
    }
    public void B060_01_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBUSE.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = WS_HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBUSE.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBUSE.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '3';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void B060_02_ADD_BUSE_PROC_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBUSE.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = WS_HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBUSE.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBUSE.END_NO;
        BPRBUSE.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
    }
    public void B060_01_ADD_BUSE_PROCESS_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = WS_TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBUSE.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCUBUSE.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBUSE.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBUSE.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = WS_TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '3';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void B060_02_ADD_BUSE_PROC_CAN_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = WS_TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBUSE.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCUBUSE.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBUSE.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBUSE.END_NO;
        BPRBUSE.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B070_01_SET_EWA_BASIC_INF();
            B070_02_SET_EWA_EVENTS();
        }
    }
    public void B070_CHALK_IT_UP_CN() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B070_01_SET_EWA_BASIC_INF();
            B070_02_SET_EWA_EVENTS_CN();
        }
    }
    public void B070_01_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B070_02_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVZUBUSE";
        BPCPOEWA.DATA.BR_OLD = BPCFTLRQ.INFO.TLR_BR;
        BPCPOEWA.DATA.BR_NEW = BPCFTLRQ.INFO.TLR_BR;
        if (BPCFBVQU.TX_DATA.TYPE == '0') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBUSE.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBUSE.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBUSE.AMT;
        }
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBUSE.BV_CODE;
        BPCPOEWA.DATA.PORT = BPCUBUSE.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B070_02_SET_EWA_EVENTS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = WS_TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = WS_TR_BRANCH;
        if (BPCFBVQU.TX_DATA.TYPE != '1') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBUSE.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBUSE.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBUSE.AMT;
        }
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBUSE.BV_CODE;
        BPCPOEWA.DATA.PORT = BPCUBUSE.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B075_CALL_FEE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START CALL FEE");
        IBS.init(SCCGWA, BPCTCALF);
        IBS.init(SCCGWA, BPCFBVF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TYPE = "SPEC ";
        BPCTCALF.INPUT_AREA.PROD_CODE = "BVF00001";
        BPCTCALF.INPUT_AREA.CPN_ID = CPN_U_BVUSE;
        BPCFBVF.BVF = "BVF";
        BPCFBVF.KIND = BPCUBUSE.BV_CODE;
        BPCFBVF.FLAG = BPCFBVQU.TX_DATA.CNT_FLG;
        BPCFBVF.COUNT = BPCFBVQU.TX_DATA.CNT_UT;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.CPN_ID);
        CEP.TRC(SCCGWA, BPCFBVF.KIND);
        CEP.TRC(SCCGWA, BPCFBVF.FLAG);
        CEP.TRC(SCCGWA, BPCFBVF.COUNT);
        if (BPCUBUSE.COUNT_MTH == '0') {
            BPCFBVF.COUNT = BPCUBUSE.PAGE_NUM;
            BPCTCALF.INPUT_AREA.TX_CNT = BPCUBUSE.BUY_NUM;
        } else {
            BPCTCALF.INPUT_AREA.TX_CNT = (short) BPCUBUSE.NUM;
        }
        CEP.TRC(SCCGWA, "CHECK-CCY");
        CEP.TRC(SCCGWA, BPCUBUSE.CCY);
        BPCTCALF.INPUT_AREA.TX_CCY = BPCUBUSE.CCY;
        BPCTCALF.INPUT_AREA.POINT_LEN = 14;
        BPCTCALF.INPUT_AREA.POINTER = BPCFBVF;
        S000_CALL_BPZFCALF();
    }
    public void B080_UPDATE_BPTTVHPB() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.TYPE == '0') {
            BPRVHPB.BV_CHK_FLG = 'N';
            BPRVHPB.BL_CHK_FLG = 'N';
            BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCRVHPB.INFO.FUNC = 'U';
            S000_CALL_BPZRVHPB();
        }
    }
    public void B090_UPDATE_TLR_ACCU() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.AC_TYP == '0') {
            IBS.init(SCCGWA, BPCFTLAM);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                BPCFTLAM.OP_CODE = 'D';
            } else {
                BPCFTLAM.OP_CODE = 'A';
            }
            BPCFTLAM.ACCU_TYP = "04";
            BPCFTLAM.TLR = BPCUBUSE.TLR;
            BPCFTLAM.CCY = BPCRBANK.LOC_CCY1;
            if (BPCFBVQU.TX_DATA.TYPE == '0') {
                BPCFTLAM.AMT = (double)BPCUBUSE.NUM;
            } else {
                BPCFTLAM.AMT = BPCUBUSE.AMT;
            }
            S000_CALL_BPZFTLAM();
        }
    }
    public void R_CHK_BV_NUM() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.HEAD_NO = "ABCDE";
            BPRTBVD.KEY.BV_CODE = BPCUBUSE.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBUSE.PVAL;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'R';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if ((BPCUBUSE.VB_FLG == '1' 
                    && BPRTBVD.NUM <= BPCFBVQU.TX_DATA.V_LMT) 
                    || (BPCUBUSE.VB_FLG == '0' 
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
            BPRTBVD.KEY.BV_CODE = BPCUBUSE.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBUSE.PVAL;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '7';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "AAAAAAAAAAAAA");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            }
            while (BPCRTBDB.RETURN_INFO != 'N') {
                WS_TOTAL_NUM += BPRTBVD.NUM;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
            }
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
            CEP.TRC(SCCGWA, "BBBBBBBBBBBB");
            if ((BPCUBUSE.VB_FLG == '1' 
                && WS_TOTAL_NUM <= BPCFBVQU.TX_DATA.V_LMT) 
                || (BPCUBUSE.VB_FLG == '0' 
                && WS_TOTAL_NUM <= BPCFBVQU.TX_DATA.B_LMT)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R_CHK_USE_METHOD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.USE_CTL);
        if (BPCFBVQU.TX_DATA.USE_CTL == '1') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
            BPRTBVD.KEY.BV_CODE = BPCUBUSE.BV_CODE;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.VALUE = BPCUBUSE.PVAL;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '7';
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            CEP.TRC(SCCGWA, BPCUBUSE.TLR);
            CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
            CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
            CEP.TRC(SCCGWA, BPCUBUSE.PVAL);
            CEP.TRC(SCCGWA, K_STS_NORMAL);
            S000_CALL_BPZRTBDB();
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "CCCCCCCCCC");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
            if (!BPRTBVD.BEG_NO.equalsIgnoreCase(BPCUBUSE.BEG_NO)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LEAST_NO_VB;
                S000_ERR_MSG_PROC();
            }
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
        }
    }
    public void R_CHK_USE_METHOD_CH() throws IOException,SQLException,Exception {
        B010_CHK_TLR_BV_HEAD_NO();
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.USE_CTL);
        if (BPCFBVQU.TX_DATA.USE_CTL == '1') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = WS_TR_BRANCH;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCUBUSE.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBUSE.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCUBUSE.HEAD_NO;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            CEP.TRC(SCCGWA, BPCUBUSE.TLR);
            CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
            CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
            CEP.TRC(SCCGWA, BPCUBUSE.PVAL);
            CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '2';
            S000_CALL_BPZRTBDB();
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "NCB1205002");
            CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
            if (!BPRTBVD.BEG_NO.equalsIgnoreCase(BPCUBUSE.BEG_NO)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LEAST_NO_VB;
                S000_ERR_MSG_PROC();
            }
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
        }
    }
    public void R_CHK_BV_LEAST_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = WS_TR_BRANCH;
        BPRTBVD.KEY.BV_CODE = BPCUBUSE.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCUBUSE.PVAL;
        BPRTBVD.BEG_NO = BPCUBUSE.BEG_NO;
        BPRTBVD.KEY.END_NO = BPCUBUSE.END_NO;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.STS = K_STS_NORMAL;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
        CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '8';
        S000_CALL_BPZRTBDB();
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCRTBDB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "13191319131913191319");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            CEP.TRC(SCCGWA, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        if (!BPRTBVD.BEG_NO.equalsIgnoreCase(BPCUBUSE.BEG_NO)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LEAST_NO_SEG;
            S000_ERR_MSG_PROC();
        }
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_P_INQ_ORG_REL, BPCPQORR);
        if (BPCPQORR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            S000_ERR_MSG_PROC();
        }
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
        CEP.TRC(SCCGWA, BPCRTBDB.RC.RC_CODE);
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
    public void S000_CALL_BPZFLTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BVLT_CHK, BPCFLTCK);
        if (BPCFLTCK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFLTCK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_VCH_CPNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_VCH_CPNT, BPCOVAWR);
        if (BPCOVAWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOVAWR.RC);
            S000_ERR_MSG_PROC();
        }
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
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
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
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
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
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHIST", BPCIFHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRVCOR() throws IOException,SQLException,Exception {
        BPCRVCOR.INFO.POINTER = BPRVCOR;
        BPCRVCOR.INFO.LEN = 126;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_VCOR, BPCRVCOR);
        if (BPCRVCOR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVCOR.RC);
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
