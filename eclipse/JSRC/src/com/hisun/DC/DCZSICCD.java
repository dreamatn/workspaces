package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSICCD {
    brParm DCTCTCDK_BR = new brParm();
    DBParm DCTCTCDK_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    DCZSICCD_WS_SQL_VARIABLES WS_SQL_VARIABLES = new DCZSICCD_WS_SQL_VARIABLES();
    DCZSICCD_WS_OUTPUT WS_OUTPUT = new DCZSICCD_WS_OUTPUT();
    DCZSICCD_WS_VARIABLES WS_VARIABLES = new DCZSICCD_WS_VARIABLES();
    DCZSICCD_WS_COND_FLG WS_COND_FLG = new DCZSICCD_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCTCDK DCRCTCDK = new DCRCTCDK();
    DCRCTCDK DCRCTCDK = new DCRCTCDK();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSICCD DCCSICCD;
    public void MP(SCCGWA SCCGWA, DCCSICCD DCCSICCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSICCD = DCCSICCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSICCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_COND_FLG.TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCSICCD.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSICCD.IO_AREA.FUNC_CD);
        if (DCCSICCD.IO_AREA.FUNC_CD == 'B') {
            B010_PLAN_BROWSE();
            if (pgmRtn) return;
        } else if (DCCSICCD.IO_AREA.FUNC_CD == 'D') {
            B020_PLAN_DELETE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "INVALID FUCNTION CODE:");
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_NO_RSLT;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
    }
    public void B010_PLAN_BROWSE() throws IOException,SQLException,Exception {
        if (DCCSICCD.IO_AREA.CARD_NO.trim().length() == 0) {
            B012_READ_CRD_INFO();
            if (pgmRtn) return;
        } else {
            B013_READ_CARD_INF();
            if (pgmRtn) return;
        }
    }
    public void B012_READ_CRD_INFO() throws IOException,SQLException,Exception {
        if (DCCSICCD.IO_AREA.START_DT == 0) {
            WS_SQL_VARIABLES.SQL_START_DT = 101;
        } else {
            WS_SQL_VARIABLES.SQL_START_DT = DCCSICCD.IO_AREA.START_DT;
        }
        if (DCCSICCD.IO_AREA.END_DT == 0) {
            WS_SQL_VARIABLES.SQL_END_DT = 99991231;
        } else {
            WS_SQL_VARIABLES.SQL_END_DT = DCCSICCD.IO_AREA.END_DT;
        }
        IBS.init(SCCGWA, DCRCTCDK);
        T000_STARTBR_DCTCTCDK();
        if (pgmRtn) return;
        T100_READNEXT_DCTCTCDK();
        if (pgmRtn) return;
        C200_OUTPUT_INI();
        if (pgmRtn) return;
        while (WS_COND_FLG.TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            C100_OUTPUT_LIST();
            if (pgmRtn) return;
            T100_READNEXT_DCTCTCDK();
            if (pgmRtn) return;
        }
        T200_ENDBR_DCTCTCDK();
        if (pgmRtn) return;
    }
    public void B013_READ_CARD_INF() throws IOException,SQLException,Exception {
        if (DCCSICCD.IO_AREA.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        IBS.init(SCCGWA, DCRCTCDK);
        T000_STARTBR_DCTCTCDK1();
        if (pgmRtn) return;
        T100_READNEXT_DCTCTCDK();
        if (pgmRtn) return;
        C200_OUTPUT_INI();
        if (pgmRtn) return;
        while (WS_COND_FLG.TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            C100_OUTPUT_LIST();
            if (pgmRtn) return;
            T100_READNEXT_DCTCTCDK();
            if (pgmRtn) return;
        }
        T200_ENDBR_DCTCTCDK();
        if (pgmRtn) return;
    }
    public void B020_PLAN_DELETE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDK);
        CEP.TRC(SCCGWA, DCCSICCD.IO_AREA.CARD_NO);
        DCRCTCDK.KEY.CARD_NO = DCCSICCD.IO_AREA.CARD_NO;
        T000_READUPD_DCTCTCDK();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRCTCDK, DCRCTCDK);
        T000_DELETE_RECORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DELETE SUCCESSFULLY");
        T000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, CICCUST);
        WS_OUTPUT.B_CARD_NO = DCRCTCDK.KEY.CARD_NO;
        CICCUST.DATA.AGR_NO = DCRCTCDK.KEY.CARD_NO;
        CICCUST.FUNC = 'A';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_OUTPUT.B_CI_NAME = CICCUST.O_DATA.O_CI_NM;
        WS_OUTPUT.B_TRADE_DT = DCRCTCDK.CHECK_DT;
        WS_OUTPUT.B_TRADE_BR = DCRCTCDK.CHECK_BR;
        WS_OUTPUT.B_TRADE_TLR = DCRCTCDK.CHECK_TLR;
        WS_OUTPUT.B_PRINT_DT = DCRCTCDK.PRINT_DT;
        WS_OUTPUT.B_PRINT_TMS = DCRCTCDK.PRINT_TMS;
        WS_OUTPUT.B_PRINT_BR = DCRCTCDK.PRINT_BR;
        WS_OUTPUT.B_PRINT_TLR = DCRCTCDK.PRINT_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 325;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_OUTPUT.B_CARD_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_CI_NAME);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_TRADE_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_TRADE_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_TRADE_TLR);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_PRINT_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_PRINT_TMS);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_PRINT_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_PRINT_TLR);
    }
    public void C200_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = MAX_COLS;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSICCD.IO_AREA.CARD_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = "DCRCTCDK";
        BPCPNHIS.INFO.FMT_ID_LEN = 130;
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = DCRCTCDK;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DCTCTCDK() throws IOException,SQLException,Exception {
        DCTCTCDK_BR.rp = new DBParm();
        DCTCTCDK_BR.rp.TableName = "DCTCTCDK";
        DCTCTCDK_BR.rp.where = "CHECK_DT >= :WS_SQL_VARIABLES.SQL_START_DT "
            + "AND CHECK_DT <= :WS_SQL_VARIABLES.SQL_END_DT";
        IBS.STARTBR(SCCGWA, DCRCTCDK, this, DCTCTCDK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCTCDK.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCTCDK1() throws IOException,SQLException,Exception {
        WS_SQL_VARIABLES.SQL_CARD_NO = DCCSICCD.IO_AREA.CARD_NO;
        DCTCTCDK_BR.rp = new DBParm();
        DCTCTCDK_BR.rp.TableName = "DCTCTCDK";
        DCTCTCDK_BR.rp.where = "CARD_NO = :WS_SQL_VARIABLES.SQL_CARD_NO";
        IBS.STARTBR(SCCGWA, DCRCTCDK, this, DCTCTCDK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCTCDK.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T100_READNEXT_DCTCTCDK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCTCDK, this, DCTCTCDK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T200_ENDBR_DCTCTCDK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCTCDK_BR);
    }
    public void T000_READUPD_DCTCTCDK() throws IOException,SQLException,Exception {
        DCTCTCDK_RD = new DBParm();
        DCTCTCDK_RD.TableName = "DCTCTCDK";
        DCTCTCDK_RD.upd = true;
        IBS.READ(SCCGWA, DCRCTCDK, DCTCTCDK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCTCDK.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCTCDK() throws IOException,SQLException,Exception {
        DCTCTCDK_RD = new DBParm();
        DCTCTCDK_RD.TableName = "DCTCTCDK";
        IBS.READ(SCCGWA, DCRCTCDK, DCTCTCDK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCTCDK.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_RECORD() throws IOException,SQLException,Exception {
        DCTCTCDK_RD = new DBParm();
        DCTCTCDK_RD.TableName = "DCTCTCDK";
        IBS.DELETE(SCCGWA, DCRCTCDK, DCTCTCDK_RD);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
            WS_VARIABLES.MSG_ID = "" + CICCUST.RC.RC_CODE;
            JIBS_tmp_int = WS_VARIABLES.MSG_ID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.MSG_ID = "0" + WS_VARIABLES.MSG_ID;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
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
