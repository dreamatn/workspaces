package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSALCS {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTLHIS_RD;
    int JIBS_tmp_int;
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    int K_MAX_PAR_CNT = 12;
    char K_FILLER_X01 = 0X01;
    String K_HIS_REMARKS = "CASH LIB ALTERNATE";
    String K_CPY_BPRCLIB = "BPRCLIB ";
    String K_CPY_BPRTLVB = "BPRTLVB ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String WS_ERR_MSG = " ";
    int WS_CCY_CNT = 0;
    int WS_PAR_CNT = 0;
    int WS_CNT = 0;
    BPZSALCS_WS_CCY_INFO WS_CCY_INFO = new BPZSALCS_WS_CCY_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRCLIB BPROCLIB = new BPRCLIB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRTLVB BPROTLVB = new BPRTLVB();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPCALCSO BPCALCSO = new BPCALCSO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPRLHIS BPRLHIS = new BPRLHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCSALCS BPCSALCS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSALCS BPCSALCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSALCS = BPCSALCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSALCS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSALCS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B020_PROCESS_ALTERNATE_FOR_CN();
        } else {
            B020_PROCESS_ALTERNATE();
        }
        B021_WRITE_LHIS();
        B030_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSALCS.MGR_TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B021_WRITE_LHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        IBS.init(SCCGWA, BPRLHIS);
        BPRLHIS.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRLHIS.KEY.TR_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRLHIS.BR_NM = BPCPQORG.CHN_NM;
        if (BPCSALCS.OUT_TLR.trim().length() == 0) {
            BPRLHIS.FROM_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPRLHIS.FROM_TLR = BPCSALCS.OUT_TLR;
        }
        BPRLHIS.TO_TLR = BPCSALCS.MGR_TLR;
        BPRLHIS.BOX_TYPE = '1';
        BPRLHIS.BOX_NO = BPCSALCS.PLBOX_NO;
        BPRLHIS.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLHIS.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRLHIS.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRLHIS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRLHIS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_BPTLHIS();
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_WRITE_BPTLHIS() throws IOException,SQLException,Exception {
        BPTLHIS_RD = new DBParm();
        BPTLHIS_RD.TableName = "BPTLHIS";
        BPTLHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRLHIS, BPTLHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR118);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLHIS  ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void B020_PROCESS_ALTERNATE_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLIBB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPCSALCS.PLBOX_NO;
        BPCTLIBB.INFO.FUNC = '2';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.CCY);
            if (BPRCLIB.BAL_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PL_NOT_CLOSE_AC;
                S000_ERR_MSG_PROC();
            }
            if (BPRCLIB.KEY.CCY.trim().length() > 0) {
                WS_CNT = WS_CNT + 1;
                WS_CCY_INFO.WS_CCY_REC[WS_CNT-1].WS_CCY = BPRCLIB.KEY.CCY;
                WS_CCY_INFO.WS_CCY_REC[WS_CNT-1].WS_CCY_AMT = BPRCLIB.BAL;
            }
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        IBS.init(SCCGWA, BPCTMOVB);
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCSALCS.OUT_TLR.trim().length() == 0) {
            BPCTMOVB.TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPCTMOVB.TLR = BPCSALCS.OUT_TLR;
        }
        BPRCMOV.MOV_TYP = '1';
        BPRCMOV.MOV_STS = '1';
        BPCTMOVB.FUNC = 'S';
        BPCTMOVB.POINTER = BPRCMOV;
        BPCTMOVB.DATA_LEN = 228;
        S000_CALL_BPZTMOVB();
        if (BPCTMOVB.RC.RC_CODE == 0 
            && BPCTMOVB.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, BPCTMOVB.RC);
            BPCTMOVB.RETURN_INFO = ' ';
            BPCTMOVB.FUNC = 'R';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            CEP.TRC(SCCGWA, BPCTMOVB);
            CEP.TRC(SCCGWA, BPCTMOVB.RC);
            CEP.TRC(SCCGWA, BPCTMOVB.RETURN_INFO);
            if (BPCTMOVB.RC.RC_CODE == 0 
                && BPCTMOVB.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HAVE_CASH_ON_ROAD;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCTMOVB.RC);
            BPCTMOVB.RETURN_INFO = ' ';
            BPCTMOVB.FUNC = 'E';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '1';
        BPRTLVB.CRNT_TLR = BPCSALCS.MGR_TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCV_TLR_EX_CS_LT_BOX;
            S000_ERR_MSG_PROC();
        } else {
            BPRTLVB.PLBOX_TP = '2';
            BPRTLVB.CRNT_TLR = BPCSALCS.MGR_TLR;
            BPCTLVBF.INFO.FUNC = 'I';
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            S000_CALL_BPZTLVBF();
            if (BPCTLVBF.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCV_TLR_EX_CS_LT_BOX;
                S000_ERR_MSG_PROC();
            } else {
                BPRTLVB.PLBOX_TP = '5';
                BPRTLVB.CRNT_TLR = BPCSALCS.MGR_TLR;
                BPCTLVBF.INFO.FUNC = 'I';
                BPCTLVBF.POINTER = BPRTLVB;
                BPCTLVBF.LEN = 187;
                S000_CALL_BPZTLVBF();
                if (BPCTLVBF.RETURN_INFO == 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RCV_TLR_EX_CS_LT_BOX;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
        BPRTLVB.KEY.BR = BPRCLIB.KEY.BR;
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'R';
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.CLONE(SCCGWA, BPRTLVB, BPROTLVB);
        BPRTLVB.CRNT_TLR = BPCSALCS.MGR_TLR;
        if (BPCSALCS.OUT_TLR.trim().length() == 0) {
            BPRTLVB.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPRTLVB.LAST_TLR = BPCSALCS.OUT_TLR;
        }
        BPRTLVB.LAST_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRTLVB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'U';
        S000_CALL_BPZTLVBF();
        B030_WRITE_HISTORY_RECORD();
        IBS.init(SCCGWA, BPCTLIBB);
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRCLIB.KEY.PLBOX_NO = BPCSALCS.PLBOX_NO;
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
            IBS.CLONE(SCCGWA, BPRCLIB, BPROCLIB);
            BPRCLIB.BAL_FLG = 'N';
            BPCTLIBB.INFO.FUNC = 'W';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            B020_01_HISTORY_RECORD();
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
    }
    public void B020_PROCESS_ALTERNATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLIBB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.KEY.PLBOX_NO = "%%%%%%";
        BPCRTLVB.INFO.FUNC = 'Q';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        BPCRTLVB.INFO.LEN = 187;
        S000_CALL_BPZRTLVB();
        WS_CNT = 0;
        while (BPCRTLVB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT += 1;
            if (BPRTLVB.PLBOX_TP == '1' 
                || BPRTLVB.PLBOX_TP == '2') {
                BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                WS_CNT = 1001;
                if (!BPRTLVB.CRNT_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR;
                    S000_ERR_MSG_PROC();
                }
                BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
                BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (BPCRTLVB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
        CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
        BPCTLIBB.INFO.FUNC = '2';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            if (BPRCLIB.BAL_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PL_NOT_CLOSE_AC;
                S000_ERR_MSG_PROC();
            }
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
            CEP.TRC(SCCGWA, BPCTLIBB.RC);
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        IBS.init(SCCGWA, BPCTMOVB);
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCTMOVB.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRCMOV.MOV_TYP = '1';
        BPRCMOV.MOV_STS = '1';
        BPCTMOVB.FUNC = 'S';
        BPCTMOVB.POINTER = BPRCMOV;
        BPCTMOVB.DATA_LEN = 228;
        S000_CALL_BPZTMOVB();
        if (BPCTMOVB.RC.RC_CODE == 0 
            && BPCTMOVB.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, BPCTMOVB.RC);
            BPCTMOVB.RETURN_INFO = ' ';
            BPCTMOVB.FUNC = 'R';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            CEP.TRC(SCCGWA, BPCTMOVB);
            CEP.TRC(SCCGWA, BPCTMOVB.RC);
            CEP.TRC(SCCGWA, BPCTMOVB.RETURN_INFO);
            if (BPCTMOVB.RC.RC_CODE == 0 
                && BPCTMOVB.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HAVE_CASH_ON_ROAD;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCTMOVB.RC);
            BPCTMOVB.RETURN_INFO = ' ';
            BPCTMOVB.FUNC = 'E';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
        }
        BPRTLVB.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
        BPRTLVB.KEY.BR = BPRCLIB.KEY.BR;
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'R';
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.CLONE(SCCGWA, BPRCLIB, BPROCLIB);
        BPRTLVB.CRNT_TLR = BPCSALCS.MGR_TLR;
        BPRTLVB.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRTLVB.LAST_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRTLVB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'U';
        S000_CALL_BPZTLVBF();
        B020_01_HISTORY_RECORD();
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCLIB;
        BPCPNHIS.INFO.TX_TYP_CD = "P904";
        BPCPNHIS.INFO.OLD_DAT_PT = BPROCLIB;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRCLIB;
        S000_CALL_BPZPNHIS();
    }
    public void B030_WRITE_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRTLVB;
        BPCPNHIS.INFO.TX_TYP_CD = "P904";
        BPCPNHIS.INFO.OLD_DAT_PT = BPROTLVB;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRTLVB;
        S000_CALL_BPZPNHIS();
    }
    public void B030_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCALCSO);
        BPCALCSO.RCV_TLR = BPCSALCS.MGR_TLR;
        BPCALCSO.CASH_TYP = BPCSALCS.CASH_TYP;
        BPCALCSO.PLBOX_TP = BPCSALCS.PLBOX_TP;
        BPCALCSO.PLBOX_NO = BPCSALCS.PLBOX_NO;
        CEP.TRC(SCCGWA, BPCALCSO.RCV_TLR);
        CEP.TRC(SCCGWA, BPCALCSO.CASH_TYP);
        CEP.TRC(SCCGWA, BPCALCSO.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCALCSO.PLBOX_NO);
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            if (WS_CCY_INFO.WS_CCY_REC[WS_CNT-1].WS_CCY.trim().length() > 0) {
                BPCALCSO.CCY_INFO[WS_CNT-1].CCY = WS_CCY_INFO.WS_CCY_REC[WS_CNT-1].WS_CCY;
                BPCALCSO.CCY_INFO[WS_CNT-1].CCY_AMT = WS_CCY_INFO.WS_CCY_REC[WS_CNT-1].WS_CCY_AMT;
            }
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCALCSO.CCY_INFO[WS_CNT-1].CCY);
            CEP.TRC(SCCGWA, BPCALCSO.CCY_INFO[WS_CNT-1].CCY_AMT);
        }
        BPCALCSO.GD_AMT = BPCSALCS.GD_AMT;
        BPCALCSO.FIL1 = K_FILLER_X01;
        BPCALCSO.BD_AMT = BPCSALCS.BD_AMT;
        BPCALCSO.FIL2 = K_FILLER_X01;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSALCS.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCALCSO;
        SCCFMT.DATA_LEN = 451;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
        if (BPCTLIBF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
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
