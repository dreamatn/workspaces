package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZPPWCD {
    DBParm DCTCDDAT_RD;
    DBParm DCTCTCDK_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_A = "DC409";
    String HIS_RMK = "UPDATE THE TXN PSWD OF DCTCDDAT";
    String CARD_INF = "DC-U-CARD-INF ";
    String U_BPZPNHIS = "BP-REC-NHIS";
    short MAX_COLS = 20;
    DCZPPWCD_WS_OUTPUT WS_OUTPUT = new DCZPPWCD_WS_OUTPUT();
    DCZPPWCD_WS_VARIABLES WS_VARIABLES = new DCZPPWCD_WS_VARIABLES();
    DCZPPWCD_WS_COND_FLG WS_COND_FLG = new DCZPPWCD_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCTCDK DCRCTCDK = new DCRCTCDK();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCPPWCD DCCPPWCD;
    public void MP(SCCGWA SCCGWA, DCCPPWCD DCCPPWCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCPPWCD = DCCPPWCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZPPWCD return!");
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
        IBS.init(SCCGWA, DCCPPWCD.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "2222");
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_READ_TCDK_INFO();
        if (pgmRtn) return;
        B300_GENERATE_PSW();
        if (pgmRtn) return;
        B400_UPDATE_PSW_INFO();
        if (pgmRtn) return;
        B500_UPDATE_DCTCTCDK();
        if (pgmRtn) return;
        B600_GET_ID_NO();
        if (pgmRtn) return;
        C000_HISTORY_RECORD();
        if (pgmRtn) return;
        C100_OUTPUT_LIST();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCPPWCD.IO_AREA.CARD_NO);
        if (DCCPPWCD.IO_AREA.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
        if (DCCPPWCD.IO_AREA.CARD_PSW.trim().length() == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_PSW_MUST_INPUT;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
    }
    public void B200_READ_TCDK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDK);
        DCRCTCDK.KEY.CARD_NO = DCCPPWCD.IO_AREA.CARD_NO;
        T100_READ_DCTCTCDK();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_CARD_NOTIN_CHK_LIST;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
    }
    public void B300_GENERATE_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '1';
        SCCTSSC.COMM_AREA_1.1_DST_ALG_MODE = '1';
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        SCCTSSC.COMM_AREA_1.1_SRC_ACCOUNT = DCCPPWCD.IO_AREA.CARD_NO;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        SCCTSSC.COMM_AREA_1.1_SRC_PIN_BLOCK = DCCPPWCD.IO_AREA.CARD_PSW;
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        SCCTSSC.COMM_AREA_1.1_ENC_MODE = "01";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_1);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_1.1_O_NEW_PIN);
        WS_VARIABLES.TEMP_PSW = SCCTSSC.COMM_AREA_1.1_O_NEW_PIN;
    }
    public void B400_UPDATE_PSW_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCPPWCD.IO_AREA.CARD_NO;
        T000_READ_UPD_DCTCDDAT();
        if (pgmRtn) return;
        WS_VARIABLES.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            IBS.CLONE(SCCGWA, DCRCDDAT, DCRCDDAT);
            DCRCDDAT.TRAN_PIN_DAT = WS_VARIABLES.TEMP_PSW;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCDDAT.PSW_TYP = 'N';
            T000_REWRITE_DCTCDDAT();
            if (pgmRtn) return;
        }
    }
    public void B500_UPDATE_DCTCTCDK() throws IOException,SQLException,Exception {
        DCRCTCDK.PRINT_TMS += 1;
        DCRCTCDK.PRINT_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDK.PRINT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCTCDK.PRINT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCTCDK();
        if (pgmRtn) return;
    }
    public void B600_GET_ID_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CEP.TRC(SCCGWA, WS_VARIABLES.CI_NO);
        CICCUST.DATA.CI_NO = WS_VARIABLES.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_VARIABLES.ID_NO = CICCUST.O_DATA.O_ID_NO;
        CEP.TRC(SCCGWA, WS_VARIABLES.ID_NO);
    }
    public void C000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCPPWCD.IO_AREA.CARD_NO;
        BPCPNHIS.INFO.TX_RMK = HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = "DCRCDDAT";
        BPCPNHIS.INFO.FMT_ID_LEN = 489;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DCRCDDAT;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCDDAT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, SCCFMT);
        WS_OUTPUT.O_CARD_NO = DCCPPWCD.IO_AREA.CARD_NO;
        WS_OUTPUT.O_CI_NAME = DCCPPWCD.IO_AREA.CI_NAME;
        WS_OUTPUT.O_ID_NO = WS_VARIABLES.ID_NO;
        SCCFMT.FMTID = OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 342;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUTPUT.O_CARD_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT.O_CI_NAME);
        CEP.TRC(SCCGWA, WS_OUTPUT.O_ID_NO);
    }
    public void T000_READ_UPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_NO_RSLT;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_REWRITE_DCTCTCDK() throws IOException,SQLException,Exception {
        DCTCTCDK_RD = new DBParm();
        DCTCTCDK_RD.TableName = "DCTCTCDK";
        IBS.REWRITE(SCCGWA, DCRCTCDK, DCTCTCDK_RD);
    }
    public void T100_READ_DCTCTCDK() throws IOException,SQLException,Exception {
        DCTCTCDK_RD = new DBParm();
        DCTCTCDK_RD.TableName = "DCTCTCDK";
        DCTCTCDK_RD.upd = true;
        IBS.READ(SCCGWA, DCRCTCDK, DCTCTCDK_RD);
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
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CARD_INF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
            WS_VARIABLES.MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
    }
    public void S000_CALL_SCZTSSC() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        if (SCCTSSC.RC != 0) {
            CEP.TRC(SCCGWA, SCCTSSC.RC);
            WS_VARIABLES.MSG_ID = "" + SCCTSSC.RC;
            JIBS_tmp_int = WS_VARIABLES.MSG_ID.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_VARIABLES.MSG_ID = "0" + WS_VARIABLES.MSG_ID;
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
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.MSG_ID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
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
