package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZHDCCD {
    DBParm DCTCTCDC_RD;
    brParm DCTCTCDC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_SQL_START_DT = 00010101;
    int WS_SQL_END_DT = 00010101;
    String WS_SQL_ID_TYP = "     ";
    String WS_SQL_ID_NO = "                                                                      ";
    char WS_SQL_FAST_FLG = ' ';
    int WS_SQL_HDOV_BR = 0;
    String K_OUTPUT_FMT = "DC441";
    String K_HIS_RMK = "HANDOVER INFO ADD OF CITIZEN CARD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String CPN_CI_CIZCUST = "CI-CIZCUST";
    short K_MAX_COLS = 20;
    DCZHDCCD_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new DCZHDCCD_WS_BROWSE_OUTPUT();
    DCZHDCCD_WS_H_OUTPUT WS_H_OUTPUT = new DCZHDCCD_WS_H_OUTPUT();
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_PROD_DES = "                                                            ";
    String WS_FMT_CDE = "     ";
    String WS_CARD_HLDR_CINO = "            ";
    String WS_OLD_REL_DR_CARD = "                   ";
    String WS_CI_NO = "            ";
    char WS_TBL_FLAG = ' ';
    char WS_ERR_FLAG = ' ';
    char WS_ERR_FLAG2 = ' ';
    char WS_ERR_FLAG3 = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICCUST CICCUST = new CICCUST();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCRCTCDC DCRCTCDO = new DCRCTCDC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCHDCCD DCCHDCCD;
    public void MP(SCCGWA SCCGWA, DCCHDCCD DCCHDCCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCHDCCD = DCCHDCCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZHDCCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCHDCCD.O_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.FUNC_M);
        if (DCCHDCCD.IO_AREA.FUNC_M == 'B') {
            B100_PLAN_BROWSE();
            if (pgmRtn) return;
        } else if (DCCHDCCD.IO_AREA.FUNC_M == 'H') {
            B200_PLAN_HANDOVER();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "INVALID FUCNTION CODE:");
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B100_PLAN_BROWSE() throws IOException,SQLException,Exception {
        B110_INPUT_CHK_PROC();
        if (pgmRtn) return;
        if (DCCHDCCD.IO_AREA.ID_TYP.trim().length() > 0 
            && DCCHDCCD.IO_AREA.ID_NO.trim().length() > 0) {
            WS_SQL_ID_TYP = DCCHDCCD.IO_AREA.ID_TYP;
            WS_SQL_ID_NO = DCCHDCCD.IO_AREA.ID_NO;
            WS_SQL_HDOV_BR = DCCHDCCD.IO_AREA.HDOV_BR;
            WS_SQL_FAST_FLG = DCCHDCCD.IO_AREA.URGT_FLG;
            WS_SQL_START_DT = DCCHDCCD.IO_AREA.START_DT;
            WS_SQL_END_DT = DCCHDCCD.IO_AREA.END_DT;
            T100_STARTBR_DCTCTCDC();
            if (pgmRtn) return;
        } else {
            WS_SQL_START_DT = DCCHDCCD.IO_AREA.START_DT;
            WS_SQL_END_DT = DCCHDCCD.IO_AREA.END_DT;
            WS_SQL_HDOV_BR = DCCHDCCD.IO_AREA.HDOV_BR;
            WS_SQL_FAST_FLG = DCCHDCCD.IO_AREA.URGT_FLG;
            T000_STARTBR_DCTCTCDC();
            if (pgmRtn) return;
        }
        T000_READNEXT_DCTCTCDC();
        if (pgmRtn) return;
        C000_OUTPUT_INI();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            C100_OUTPUT_LIST();
            if (pgmRtn) return;
            T000_READNEXT_DCTCTCDC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCTCDC();
        if (pgmRtn) return;
    }
    public void B110_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.START_DT);
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.END_DT);
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.ID_TYP);
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.ID_NO);
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.HDOV_BR);
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.URGT_FLG);
        if (DCCHDCCD.IO_AREA.START_DT != 0 
            && DCCHDCCD.IO_AREA.END_DT != 0 
            && DCCHDCCD.IO_AREA.START_DT > DCCHDCCD.IO_AREA.END_DT) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_START_END_DATE);
        }
        if ((DCCHDCCD.IO_AREA.ID_NO.trim().length() == 0 
            && DCCHDCCD.IO_AREA.ID_TYP.trim().length() > 0) 
            || (DCCHDCCD.IO_AREA.ID_NO.trim().length() > 0 
            && DCCHDCCD.IO_AREA.ID_TYP.trim().length() == 0)) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_IDTYPE_IDNO_SAMETIME);
        }
        if (DCCHDCCD.IO_AREA.URGT_FLG == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_URGT_FLG;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B200_PLAN_HANDOVER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDC);
        CEP.TRC(SCCGWA, DCCHDCCD.IO_AREA.OLD_CARD);
        DCRCTCDC.KEY.OLD_CARD_NO = DCCHDCCD.IO_AREA.OLD_CARD;
        T000_READUPD_DCTCTCDC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, DCRCTCDC, DCRCTCDO);
        DCRCTCDC.CHG_STS = '2';
        DCRCTCDC.HDOV_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.HDOV_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCTCDC.HDOV_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCTCDC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTCTCDC();
        if (pgmRtn) return;
        B300_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B400_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void C000_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = K_MAX_COLS;
        SCCMPAG.SCR_ROW_CNT = 25;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_B_CHG_SEQ = DCRCTCDC.CHG_SEQ;
        WS_BROWSE_OUTPUT.WS_B_OLD_CARD_NO = DCRCTCDC.KEY.OLD_CARD_NO;
        WS_BROWSE_OUTPUT.WS_B_NEW_CARD_NO = DCRCTCDC.NEW_CARD_NO;
        WS_BROWSE_OUTPUT.WS_B_CHG_STS = DCRCTCDC.CHG_STS;
        if (DCRCTCDC.CI_NM.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.ID_TYPE = DCRCTCDC.ID_TYP;
            CICCUST.DATA.ID_NO = DCRCTCDC.ID_NO;
            CICCUST.FUNC = 'I';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_BROWSE_OUTPUT.WS_B_CI_NAME = CICCUST.O_DATA.O_CI_NM;
        } else {
            WS_BROWSE_OUTPUT.WS_B_CI_NAME = DCRCTCDC.CI_NM;
        }
        WS_BROWSE_OUTPUT.WS_B_ID_TYPE = DCRCTCDC.ID_TYP;
        WS_BROWSE_OUTPUT.WS_B_ID_NO = DCRCTCDC.ID_NO;
        WS_BROWSE_OUTPUT.WS_B_TRADE_DT = DCRCTCDC.TXN_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 392;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_CHG_SEQ);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_OLD_CARD_NO);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_NEW_CARD_NO);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_CHG_STS);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_CI_NAME);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_ID_TYPE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_ID_NO);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_B_TRADE_DT);
    }
    public void B300_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_H_OUTPUT);
        WS_H_OUTPUT.WS_H_CARD_NO = DCRCTCDC.KEY.OLD_CARD_NO;
        WS_H_OUTPUT.WS_H_CHG_STS = DCRCTCDC.CHG_STS;
        WS_H_OUTPUT.WS_H_HDOV_DT = DCRCTCDC.HDOV_DT;
        WS_H_OUTPUT.WS_H_HDOV_BR = DCRCTCDC.HDOV_BR;
        WS_H_OUTPUT.WS_H_HDOV_TLR = DCRCTCDC.HDOV_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_H_OUTPUT;
        SCCFMT.DATA_LEN = 42;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B400_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCHDCCD.IO_AREA.OLD_CARD;
        BPCPNHIS.INFO.AC = DCCHDCCD.IO_AREA.OLD_CARD;
        BPCPNHIS.INFO.TX_TYP_CD = "PB10";
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = "DCRCTCDC";
        BPCPNHIS.INFO.FMT_ID_LEN = 971;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DCRCTCDO;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCTCDC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZCUST, CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void T000_READUPD_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.upd = true;
        IBS.READ(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCTCDC.KEY.OLD_CARD_NO);
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        IBS.REWRITE(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
    }
    public void T000_STARTBR_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_BR.rp = new DBParm();
        DCTCTCDC_BR.rp.TableName = "DCTCTCDC";
        DCTCTCDC_BR.rp.where = "( TXN_DT >= :WS_SQL_START_DT "
            + "AND TXN_DT <= :WS_SQL_END_DT ) "
            + "AND ( FAST_FLG = :WS_SQL_FAST_FLG ) "
            + "AND ( CHG_STS = '1' ) "
            + "AND ( APP_BR = :WS_SQL_HDOV_BR ) "
            + "AND ( CHG_TYP = '1' )";
        DCTCTCDC_BR.rp.order = "TXN_DT DESC";
        IBS.STARTBR(SCCGWA, DCRCTCDC, this, DCTCTCDC_BR);
    }
    public void T100_STARTBR_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_BR.rp = new DBParm();
        DCTCTCDC_BR.rp.TableName = "DCTCTCDC";
        DCTCTCDC_BR.rp.where = "( TXN_DT >= :WS_SQL_START_DT "
            + "AND TXN_DT <= :WS_SQL_END_DT ) "
            + "AND FAST_FLG = :WS_SQL_FAST_FLG "
            + "AND CHG_STS = '1' "
            + "AND APP_BR = :WS_SQL_HDOV_BR "
            + "AND ID_TYP = :WS_SQL_ID_TYP "
            + "AND ID_NO = :WS_SQL_ID_NO "
            + "AND CHG_TYP = '1'";
        DCTCTCDC_BR.rp.order = "TXN_DT DESC";
        IBS.STARTBR(SCCGWA, DCRCTCDC, this, DCTCTCDC_BR);
    }
    public void T200_STARTBR_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_BR.rp = new DBParm();
        DCTCTCDC_BR.rp.TableName = "DCTCTCDC";
        DCTCTCDC_BR.rp.where = "FAST_FLG = :WS_SQL_FAST_FLG "
            + "AND CHG_STS = '1' "
            + "AND APP_BR = :WS_SQL_HDOV_BR "
            + "AND CHG_TYP = '1'";
        DCTCTCDC_BR.rp.order = "TXN_DT DESC";
        IBS.STARTBR(SCCGWA, DCRCTCDC, this, DCTCTCDC_BR);
    }
    public void T000_READNEXT_DCTCTCDC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCTCDC, this, DCTCTCDC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCTCTCDC NOT FOUND2:");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCTCDC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCTCDC_BR);
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
