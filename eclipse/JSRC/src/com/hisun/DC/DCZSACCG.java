package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSACCG {
    DBParm DCTCDDAT_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_B = "DC801";
    String K_OUTPUT_FMT_M = "DC802";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    short K_MAX_COLS = 20;
    DCZSACCG_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new DCZSACCG_WS_BROWSE_OUTPUT();
    DCZSACCG_WS_MOD_OUTPUT WS_MOD_OUTPUT = new DCZSACCG_WS_MOD_OUTPUT();
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_PROD_DES = "                                                            ";
    String WS_FMT_CDE = "     ";
    String WS_CARD_HLDR_CINO = "            ";
    String WS_OLD_REL_DR_CARD = "                   ";
    String WS_LNK_CARD_HLDR_CINO = "            ";
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
    CICQACRL CICQACRL = new CICQACRL();
    CICSACRL CICSACRL = new CICSACRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCCSACCG DCCSACCG;
    public void MP(SCCGWA SCCGWA, DCCSACCG DCCSACCG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSACCG = DCCSACCG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSACCG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCSACCG.O_AREA);
        CEP.TRC(SCCGWA, DCCSACCG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DCCSACCG.IO_AREA.FUNC_M);
        if (DCCSACCG.IO_AREA.FUNC_M == 'B') {
            B100_PLAN_BROWSE();
            if (pgmRtn) return;
        } else if (DCCSACCG.IO_AREA.FUNC_M == 'M') {
            B200_PLAN_MOD();
            if (pgmRtn) return;
        } else {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B100_PLAN_BROWSE() throws IOException,SQLException,Exception {
        B101_INPUT_CHK_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSACCG.IO_AREA.DR_CARD;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
        if (WS_TBL_FLAG == 'Y') {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCSACCG.IO_AREA.DR_CARD;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_PROD_FLG == 'Y') {
                C100_OUTPUT_LIST();
                if (pgmRtn) return;
            } else {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NOT_ALIEN_CARD;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            }
        }
    }
    public void B200_PLAN_MOD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSACCG.IO_AREA.DR_CARD);
        CEP.TRC(SCCGWA, DCCSACCG.IO_AREA.LNK_CARD);
        CEP.TRC(SCCGWA, DCCSACCG.IO_AREA.ID_NO);
        if (DCCSACCG.IO_AREA.LNK_CARD.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CARD_MST_INPUT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
            if (DCCSACCG.IO_AREA.LNK_CARD.equalsIgnoreCase(DCCSACCG.IO_AREA.DR_CARD)) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_LNK_DR_SAME;
                CEP.ERR(SCCGWA, WS_MSG_ID);
            } else {
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCCSACCG.IO_AREA.LNK_CARD;
                T000_READ_DCTCDDAT();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'N') {
                    WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
                    CEP.ERR(SCCGWA, WS_MSG_ID);
                } else {
                    WS_LNK_CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
                }
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCCSACCG.IO_AREA.DR_CARD;
                T000_READ_DCTCDDAT();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'N') {
                    WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
                    CEP.ERR(SCCGWA, WS_MSG_ID);
                } else {
                    WS_CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
                }
                CEP.TRC(SCCGWA, WS_CARD_HLDR_CINO);
                CEP.TRC(SCCGWA, WS_LNK_CARD_HLDR_CINO);
                if (!WS_LNK_CARD_HLDR_CINO.equalsIgnoreCase(WS_CARD_HLDR_CINO)) {
                    WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CINO_NOT_SAME;
                    CEP.ERR(SCCGWA, WS_MSG_ID);
                } else {
                    C400_GET_LNK_CRD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, WS_OLD_REL_DR_CARD);
                    CEP.TRC(SCCGWA, "DELETE LINK INFO");
                    IBS.init(SCCGWA, CICSACRL);
                    CICSACRL.FUNC = 'D';
                    CICSACRL.DATA.AC_NO = DCCSACCG.IO_AREA.DR_CARD;
                    CICSACRL.DATA.REL_AC_NO = WS_OLD_REL_DR_CARD;
                    CICSACRL.DATA.AC_REL = "03";
                    S000_CALL_CIZSACRL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "NEW LINK INFO");
                    IBS.init(SCCGWA, CICSACRL);
                    CICSACRL.FUNC = 'A';
                    CICSACRL.DATA.AC_NO = DCCSACCG.IO_AREA.DR_CARD;
                    CICSACRL.DATA.REL_AC_NO = DCCSACCG.IO_AREA.LNK_CARD;
                    CICSACRL.DATA.AC_REL = "03";
                    CICSACRL.DATA.DEFAULT = '1';
                    S000_CALL_CIZSACRL();
                    if (pgmRtn) return;
                }
            }
        }
        if (DCCSACCG.IO_AREA.PASS_WRD.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_TX_PASSWD_NEEDED;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
        C200_OUTPUT_LIST();
        if (pgmRtn) return;
        B600_REC_HIS();
        if (pgmRtn) return;
    }
    public void B101_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "MJL TEST  :");
        CEP.TRC(SCCGWA, DCCSACCG.IO_AREA.DR_CARD);
        if (DCCSACCG.IO_AREA.DR_CARD.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CARD_MST_INPUT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B600_REC_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.TX_TOOL = DCCSACCG.IO_AREA.DR_CARD;
        BPCPNHIS.INFO.CI_NO = DCCSACCG.IO_AREA.ID_NO;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.TX_RMK = "UPDATE ALIEN CARD LINK CARD";
        if (BPCPNHIS.INFO.TX_TYP == 'M') {
            BPCPNHIS.INFO.OLD_DAT_PT = DCCSACCG;
        }
        BPCPNHIS.INFO.NEW_DAT_PT = WS_MOD_OUTPUT;
        BPCPNHIS.INFO.FMT_ID = "DCZSACCG";
        BPCPNHIS.INFO.FMT_ID_LEN = 408;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        C300_OUTPUT_INI();
        if (pgmRtn) return;
        WS_FMT_CDE = K_OUTPUT_FMT_B;
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, CICQACRL);
        WS_CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        CICCUST.DATA.CI_NO = WS_CARD_HLDR_CINO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_B_ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        WS_BROWSE_OUTPUT.WS_B_ID_NO = CICCUST.O_DATA.O_ID_NO;
        WS_BROWSE_OUTPUT.WS_B_CI_NM = CICCUST.O_DATA.O_CI_NM;
        WS_BROWSE_OUTPUT.WS_B_DR_CARD = DCCSACCG.IO_AREA.DR_CARD;
        C400_GET_LNK_CRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 367;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C200_OUTPUT_LIST() throws IOException,SQLException,Exception {
        WS_FMT_CDE = K_OUTPUT_FMT_M;
        IBS.init(SCCGWA, WS_MOD_OUTPUT);
        WS_MOD_OUTPUT.WS_M_DR_CARD = DCCSACCG.IO_AREA.DR_CARD;
        WS_MOD_OUTPUT.WS_M_REL_DR_CARD = DCCSACCG.IO_AREA.LNK_CARD;
        SCCFMT.DATA_PTR = WS_MOD_OUTPUT;
        SCCFMT.DATA_LEN = 38;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void C300_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = K_MAX_COLS;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C400_GET_LNK_CRD() throws IOException,SQLException,Exception {
        CICQACRL.DATA.AC_NO = DCCSACCG.IO_AREA.DR_CARD;
        CICQACRL.DATA.AC_REL = "03";
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_B_REL_DR_CARD = CICQACRL.O_DATA.O_REL_AC_NO;
        WS_OLD_REL_DR_CARD = CICQACRL.O_DATA.O_REL_AC_NO;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
            WS_MSG_ID = "" + CICCUST.RC.RC_CODE;
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSG_ID = "0" + WS_MSG_ID;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            WS_MSG_ID = "" + CICQACRL.RC.RC_CODE;
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSG_ID = "0" + WS_MSG_ID;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICSACRL.RC.RC_CODE);
            WS_MSG_ID = "" + CICSACRL.RC.RC_CODE;
            JIBS_tmp_int = WS_MSG_ID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSG_ID = "0" + WS_MSG_ID;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
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
