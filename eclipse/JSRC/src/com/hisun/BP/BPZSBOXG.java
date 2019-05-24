package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBOXG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTLVB_RD;
    DBParm BPTCLIB_RD;
    brParm BPTCLIB_BR = new brParm();
    brParm BPTVHPB_BR = new brParm();
    DBParm BPTVHPB_RD;
    String K_OUTPUT_FMT = "BP222";
    int K_SIGN_ON_FIRST = 1;
    char K_CASH_BOX_FLAG = '0';
    char K_BV_BOX_FLAG = '1';
    char K_F_CASH_LIB_FLG = '1';
    char K_Z_CASH_LIB_FLAG = '2';
    char K_F_CASH_BOX_FLAG = '3';
    char K_LONGTOU_BOX_FLAG = '5';
    char K_BV_LIB_FLAG = '1';
    char K_STSW_FLG_Y = '1';
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN  ";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_R_ADW_BOXP = "BP-R-ADW-BOXP";
    String WS_ERR_MSG = " ";
    String WS_CASH_BOX_NO = " ";
    String WS_BV_BOX_NO = " ";
    BPZSBOXG_WS_OUTPUT_DETAIL WS_OUTPUT_DETAIL = new BPZSBOXG_WS_OUTPUT_DETAIL();
    int WS_CNT = 0;
    char WS_CASH_BOX_PLAN_FLAG = ' ';
    char WS_BV_BOX_PLAN_FLAG = ' ';
    char WS_TLR_GOT_BOX_FLAG = ' ';
    char WS_BOX_INUSED_FLAG = ' ';
    char WS_TLR_STSW_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRBOXP BPRBOXP = new BPRBOXP();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCRBOXP BPCRBOXP = new BPCRBOXP();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    SCCGWA SCCGWA;
    BPCSBOXG BPCSBOXG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBOXG BPCSBOXG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBOXG = BPCSBOXG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBOXG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_TLR_SIGN_TIMES();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_TIMES);
        if (BPCFTLRQ.INFO.SIGN_TIMES == K_SIGN_ON_FIRST) {
            B020_GET_CASH_BOX_PLAN();
            if (WS_CASH_BOX_PLAN_FLAG == 'Y') {
                B030_CHECK_TLR_AND_CASHBOX();
                if ((WS_TLR_GOT_BOX_FLAG != 'C') 
                    && (WS_BOX_INUSED_FLAG != 'C') 
                    && (WS_TLR_STSW_FLAG == 'C')) {
                    B040_PROCESS_CASH_BOX();
                }
            }
            B050_GET_BV_BOX_PLAN();
            if (WS_BV_BOX_PLAN_FLAG == 'Y') {
                B060_CHECK_TLR_AND_BVBOX();
                if ((WS_TLR_GOT_BOX_FLAG != 'B') 
                    && (WS_BOX_INUSED_FLAG != 'B') 
                    && (WS_TLR_STSW_FLAG == 'B')) {
                    B070_PROCESS_BV_BOX();
                }
            }
            IBS.init(SCCGWA, BPRTLVB);
            IBS.init(SCCGWA, BPCRTLVB);
            BPRTLVB.KEY.BR = BPCSBOXG.BR;
            BPRTLVB.CRNT_TLR = BPCSBOXG.TLR;
            T000_READ_BPTTLVB();
            IBS.init(SCCGWA, BPRCLIB);
            BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
            T000_STARTBR_BPTCLIB();
            T000_READNEXT_BPTCLIB();
            for (WS_CNT = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_CNT += 1) {
                BPRCLIB.BAL_FLG = 'N';
                T000_REWRITE_BPTCLIB();
                T000_READNEXT_BPTCLIB();
            }
            T000_ENDBR_BPTCLIB();
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = BPCSBOXG.BR;
            BPRVHPB.CUR_TLR = BPCSBOXG.TLR;
            T000_STARTBR_BPTVHPB();
            T000_READNEXT_BPTVHPB();
            for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N'; WS_CNT += 1) {
                BPRVHPB.BV_CHK_FLG = 'N';
                T000_REWRITE_BPTVHPB();
                T000_READNEXT_BPTVHPB();
            }
            T000_ENDBR_BPTVHPB();
        }
        B080_GET_CSBV_LIB_BOX_NO();
    }
    public void B010_CHECK_TLR_SIGN_TIMES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
    }
    public void B020_GET_CASH_BOX_PLAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBOXP);
        IBS.init(SCCGWA, BPCRBOXP);
        BPRBOXP.KEY.PLAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBOXP.KEY.BOX_TYPE = K_CASH_BOX_FLAG;
        BPRBOXP.PLAN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRBOXP.INFO.FUNC = 'Q';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
        if (BPCRBOXP.RETURN_INFO == 'F' 
            && BPRBOXP.RECV_FLG == 'N') {
            WS_CASH_BOX_PLAN_FLAG = 'Y';
            WS_CASH_BOX_NO = BPRBOXP.KEY.BOX_NO;
        } else {
            WS_CASH_BOX_PLAN_FLAG = 'N';
        }
    }
    public void B030_CHECK_TLR_AND_CASHBOX() throws IOException,SQLException,Exception {
        if (WS_CASH_BOX_NO == null) WS_CASH_BOX_NO = "";
        JIBS_tmp_int = WS_CASH_BOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_CASH_BOX_NO += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (WS_CASH_BOX_NO.substring(0, 1).equalsIgnoreCase("3") 
            && BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_TLR_STSW_FLAG = 'C';
        }
        if (WS_CASH_BOX_NO == null) WS_CASH_BOX_NO = "";
        JIBS_tmp_int = WS_CASH_BOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_CASH_BOX_NO += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (WS_CASH_BOX_NO.substring(0, 1).equalsIgnoreCase("5") 
            && BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            && BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_TLR_STSW_FLAG = 'C';
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '3';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        if (BPCTLVBF.RETURN_INFO == 'F' 
            && BPRTLVB.KEY.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_TLR_GOT_BOX_FLAG = 'C';
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '5';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        if (BPCTLVBF.RETURN_INFO == 'F' 
            && BPRTLVB.KEY.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_TLR_GOT_BOX_FLAG = 'C';
        }
        if (WS_TLR_GOT_BOX_FLAG != 'C') {
            IBS.init(SCCGWA, BPRTLVB);
            IBS.init(SCCGWA, BPCTLVBF);
            BPRTLVB.KEY.PLBOX_NO = WS_CASH_BOX_NO;
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCTLVBF.INFO.FUNC = 'Q';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            CEP.TRC(SCCGWA, BPRTLVB.CRNT_TLR);
            if (BPCTLVBF.RETURN_INFO == 'F' 
                && BPRTLVB.CRNT_TLR.trim().length() > 0) {
                WS_BOX_INUSED_FLAG = 'C';
            }
        }
    }
    public void B040_PROCESS_CASH_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.KEY.PLBOX_NO = WS_CASH_BOX_NO;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCTLVBF.INFO.FUNC = 'R';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'F') {
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRTLVB.RLTD_FLG = 'N';
            BPRTLVB.LAST_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTLVB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.INFO.FUNC = 'U';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = WS_CASH_BOX_NO;
        BPCTLIBB.INFO.FUNC = '1';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            BPRCLIB.BAL_FLG = 'N';
            BPRCLIB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLIBB.INFO.FUNC = 'W';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        IBS.init(SCCGWA, BPRBOXP);
        IBS.init(SCCGWA, BPCRBOXP);
        BPRBOXP.KEY.PLAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBOXP.KEY.BOX_TYPE = K_CASH_BOX_FLAG;
        BPRBOXP.KEY.BOX_NO = WS_CASH_BOX_NO;
        BPCRBOXP.INFO.FUNC = 'R';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
        BPRBOXP.RECV_FLG = 'Y';
        BPRBOXP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBOXP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCRBOXP.INFO.FUNC = 'U';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
    }
    public void B050_GET_BV_BOX_PLAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBOXP);
        IBS.init(SCCGWA, BPCRBOXP);
        BPRBOXP.KEY.PLAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBOXP.KEY.BOX_TYPE = K_BV_BOX_FLAG;
        BPRBOXP.PLAN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRBOXP.INFO.FUNC = 'Q';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
        if (BPCRBOXP.RETURN_INFO == 'F' 
            && BPRBOXP.RECV_FLG == 'N') {
            WS_BV_BOX_PLAN_FLAG = 'Y';
            WS_BV_BOX_NO = BPRBOXP.KEY.BOX_NO;
        } else {
            WS_BV_BOX_PLAN_FLAG = 'N';
        }
    }
    public void B060_CHECK_TLR_AND_BVBOX() throws IOException,SQLException,Exception {
        if (WS_BV_BOX_NO == null) WS_BV_BOX_NO = "";
        JIBS_tmp_int = WS_BV_BOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_BV_BOX_NO += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (WS_BV_BOX_NO.substring(0, 1).equalsIgnoreCase("0") 
            && BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_TLR_STSW_FLAG = 'B';
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = WS_BV_BOX_NO;
        BPCRVHPB.INFO.FUNC = 'Q';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        if (BPCRVHPB.RETURN_INFO == 'F' 
            && BPRVHPB.RELATE_FLG == 'Y') {
            WS_BOX_INUSED_FLAG = 'B';
        } else {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRVHPB.POLL_BOX_IND = '0';
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            BPCRVHPB.INFO.LEN = 152;
            BPCRVHPB.INFO.POINTER = BPRVHPB;
            S000_CALL_BPZRVHPB();
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TLR_GOT_BOX_FLAG = 'B';
            }
        }
    }
    public void B070_PROCESS_BV_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = WS_BV_BOX_NO;
        BPCRVHPB.INFO.FUNC = 'R';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.BV_CHK_FLG = 'N';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRVHPB.INFO.FUNC = 'U';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        IBS.init(SCCGWA, BPRBOXP);
        IBS.init(SCCGWA, BPCRBOXP);
        BPRBOXP.KEY.PLAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBOXP.KEY.BOX_TYPE = K_BV_BOX_FLAG;
        BPRBOXP.KEY.BOX_NO = WS_BV_BOX_NO;
        BPCRBOXP.INFO.FUNC = 'R';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
        BPRBOXP.RECV_FLG = 'Y';
        BPRBOXP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBOXP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBOXP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCRBOXP.INFO.FUNC = 'U';
        BPCRBOXP.INFO.POINTER = BPRBOXP;
        BPCRBOXP.INFO.LEN = 116;
        S000_CALL_BPZRBOXP();
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND ( CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        BPTTLVB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
    }
    public void T000_READ_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        BPTCLIB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND PLBOX_NO = :BPRTLVB.KEY.PLBOX_NO "
            + "AND CASH_TYP = '01' "
            + "AND CCY = :BPRCLIB.KEY.CCY";
        BPTCLIB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRCLIB, this, BPTCLIB_RD);
    }
    public void T000_STARTBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :BPRCLIB.KEY.BR "
            + "AND PLBOX_NO = :BPRCLIB.KEY.PLBOX_NO";
        BPTCLIB_BR.rp.errhdl = true;
        BPTCLIB_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void T000_REWRITE_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_RD = new DBParm();
        BPTCLIB_RD.TableName = "BPTCLIB";
        IBS.REWRITE(SCCGWA, BPRCLIB, BPTCLIB_RD);
    }
    public void T000_STARTBR_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_BR.rp = new DBParm();
        BPTVHPB_BR.rp.TableName = "BPTVHPB";
        BPTVHPB_BR.rp.where = "BR = :BPRVHPB.BR "
            + "AND CUR_TLR = :BPRVHPB.CUR_TLR";
        BPTVHPB_BR.rp.errhdl = true;
        BPTVHPB_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRVHPB, this, BPTVHPB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRVHPB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRVHPB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRVHPB, this, BPTVHPB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRVHPB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRVHPB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTVHPB_BR);
    }
    public void T000_REWRITE_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        IBS.REWRITE(SCCGWA, BPRVHPB, BPTVHPB_RD);
    }
    public void B080_GET_CSBV_LIB_BOX_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRTLVB.INFO.FUNC = 'L';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        while (BPCRTLVB.RETURN_INFO != 'N') {
            if (BPRTLVB.KEY.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                if (BPRTLVB.PLBOX_TP == K_F_CASH_LIB_FLG 
                    || BPRTLVB.PLBOX_TP == K_Z_CASH_LIB_FLAG) {
                    WS_OUTPUT_DETAIL.WS_OUT_CS_LIB_NO = BPRTLVB.KEY.PLBOX_NO;
                }
                if (BPRTLVB.PLBOX_TP == K_F_CASH_BOX_FLAG 
                    || BPRTLVB.PLBOX_TP == K_LONGTOU_BOX_FLAG) {
                    WS_OUTPUT_DETAIL.WS_OUT_CS_BOX_NO = BPRTLVB.KEY.PLBOX_NO;
                }
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            S000_CALL_BPZRTLVB();
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_OUT_CS_LIB_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_OUT_CS_BOX_NO);
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'B';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        BPCRVHPB.INFO.FUNC = 'N';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        while (BPCRVHPB.RETURN_INFO != 'N') {
            if (BPRVHPB.POLL_BOX_IND == K_BV_LIB_FLAG) {
                WS_OUTPUT_DETAIL.WS_OUT_BV_LIB_NO = BPRVHPB.KEY.POOL_BOX_NO;
            } else {
                WS_OUTPUT_DETAIL.WS_OUT_BV_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            }
            BPCRVHPB.INFO.FUNC = 'N';
            BPCRVHPB.INFO.LEN = 152;
            BPCRVHPB.INFO.POINTER = BPRVHPB;
            S000_CALL_BPZRVHPB();
        }
        BPCRVHPB.INFO.FUNC = 'E';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_OUT_BV_LIB_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_OUT_BV_BOX_NO);
        B090_OUTPUT_TLR_CSBV_LIBBOX_NO();
    }
    public void B090_OUTPUT_TLR_CSBV_LIBBOX_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DETAIL;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBOXP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BOXP, BPCRBOXP);
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
