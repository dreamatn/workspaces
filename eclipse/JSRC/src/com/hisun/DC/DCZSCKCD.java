package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCKCD {
    DBParm DCTCDDAT_RD;
    DBParm DCTCTCDK_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String OUTPUT_FMT_A = "DC445";
    String HIS_RMK = "CITIZEN CARD CHECKING";
    String TRKDAT_CHK = "DC-P-CARD-TRKDAT-CHK";
    String CARD_INF = "DC-U-CARD-INF ";
    String U_BPZPNHIS = "BP-REC-NHIS";
    DCZSCKCD_WS_OUTPUT WS_OUTPUT = new DCZSCKCD_WS_OUTPUT();
    DCZSCKCD_WS_VARIABLES WS_VARIABLES = new DCZSCKCD_WS_VARIABLES();
    DCZSCKCD_WS_COND_FLG WS_COND_FLG = new DCZSCKCD_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCTCDK DCRCTCDK = new DCRCTCDK();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCSCKCD DCCSCKCD;
    public void MP(SCCGWA SCCGWA, DCCSCKCD DCCSCKCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCKCD = DCCSCKCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCKCD return!");
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
        IBS.init(SCCGWA, DCCSCKCD.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "2222");
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_READ_CRD_INFO();
        if (pgmRtn) return;
        B400_ADD_TCDK_INFO();
        if (pgmRtn) return;
        C000_HISTORY_RECORD();
        if (pgmRtn) return;
        C100_OUTPUT_LIST();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCSCKCD.IO_AREA.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        }
    }
    public void B200_READ_CRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCKCD.IO_AREA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'Y') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCSCKCD.IO_AREA.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_PROD_FLG == 'S') {
            } else {
                WS_VARIABLES.MSG_ID = ERROR_MSG.DC_NOT_CITIZEN_CARD;
                CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
            }
        }
    }
    public void B400_ADD_TCDK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDK);
        DCRCTCDK.KEY.CARD_NO = DCCSCKCD.IO_AREA.CARD_NO;
        DCRCTCDK.CHECK_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDK.CHECK_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCTCDK.CHECK_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCTCDK.PRINT_DT = 10101;
        DCRCTCDK.PRINT_TMS = 0;
        DCRCTCDK.PRINT_BR = 0;
        DCRCTCDK.PRINT_TLR = " ";
        T000_WRITE_DCTCTCDK();
        if (pgmRtn) return;
    }
    public void C000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = HIS_RMK;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCRCTCDK";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSCKCD.IO_AREA.CARD_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 130;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCTCDK;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.B_CARD_NO = DCCSCKCD.IO_AREA.CARD_NO;
        WS_OUTPUT.B_CI_NAME = DCCSCKCD.IO_AREA.CI_NAME;
        WS_OUTPUT.B_UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.B_UPT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_OUTPUT.B_UPT_TELLER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 294;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_CARD_NO);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_CI_NAME);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_UPT_DT);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_UPT_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.B_UPT_TELLER);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CARD_INF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
            WS_VARIABLES.MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
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
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
            WS_VARIABLES.MSG_ID = ERROR_MSG.DC_CARD_NOT_EXIST;
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
    public void T000_WRITE_DCTCTCDK() throws IOException,SQLException,Exception {
        DCTCTCDK_RD = new DBParm();
        DCTCTCDK_RD.TableName = "DCTCTCDK";
        DCTCTCDK_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRCTCDK, DCTCTCDK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DUPKEY;
            CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
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
