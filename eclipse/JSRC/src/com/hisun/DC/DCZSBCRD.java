package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBCRD {
    DBParm DCTCDORD_RD;
    DBParm DCTCTCDC_RD;
    DBParm DCTCITCD_RD;
    brParm DCTCDDAT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_B = "DC060";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_SEARCH_CI = "CI-SEARCH-CI";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    short K_MAX_COLS = 20;
    DCZSBCRD_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new DCZSBCRD_WS_BROWSE_OUTPUT();
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    String WS_PROD_DES = "                                                            ";
    String WS_FMT_CDE = "     ";
    String WS_CARD_HLDR_CINO = "            ";
    String WS_OLD_REL_DR_CARD = "                   ";
    String WS_CI_NO = "            ";
    char WS_SHOW_FLG = ' ';
    String WS_OLD_CARD_NO = " ";
    String WS_HDOV_TLR = " ";
    int WS_HDOV_DT = 0;
    int WS_HDOV_BR = 0;
    String WS_SOCIAL_NO = " ";
    String WS_SOCIAL_CARD_NO = " ";
    String WS_E_CARD_NO = " ";
    String WS_OLD_SOCIAL_CARD_NO = " ";
    String WS_OLD_E_CARD_NO = " ";
    String WS_NEW_CARD_NO = " ";
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
    CICSSCH CICSSCH = new CICSSCH();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCRCITCD DCRCITCD = new DCRCITCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DCCSBCRD DCCSBCRD;
    public void MP(SCCGWA SCCGWA, DCCSBCRD DCCSBCRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBCRD = DCCSBCRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBCRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_TBL_FLAG = 'Y';
        IBS.init(SCCGWA, DCCSBCRD.O_AREA);
        CEP.TRC(SCCGWA, DCCSBCRD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, DCCSBCRD.IO_AREA.FUNC_M);
        if (DCCSBCRD.IO_AREA.FUNC_M == 'B') {
            B100_PLAN_BROWSE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "INVALID FUCNTION CODE:");
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B100_PLAN_BROWSE() throws IOException,SQLException,Exception {
        B101_INPUT_CHK_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCSBCRD.IO_AREA.ID_TYP);
        CEP.TRC(SCCGWA, DCCSBCRD.IO_AREA.ID_NO);
        CEP.TRC(SCCGWA, DCCSBCRD.IO_AREA.CI_NAME);
        IBS.init(SCCGWA, CICSSCH);
        CICSSCH.INPUT_DATA.I_ID_TYPE = DCCSBCRD.IO_AREA.ID_TYP;
        CICSSCH.INPUT_DATA.I_ID_NO = DCCSBCRD.IO_AREA.ID_NO;
        CICSSCH.INPUT_DATA.I_CI_NM = DCCSBCRD.IO_AREA.CI_NAME;
        CICSSCH.FUNC = 'B';
        S000_CALL_CIZSSCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSSCH.OUTPUT_DATA.O_CI_NO);
        DCRCDDAT.CARD_HLDR_CINO = CICSSCH.OUTPUT_DATA.O_CI_NO;
        WS_CI_NO = CICSSCH.OUTPUT_DATA.O_CI_NO;
        T000_STARTBR_DCTCDDAT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NO_RSLT;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
        C000_OUTPUT_INI();
        if (pgmRtn) return;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_SHOW_FLG = 'Y';
            if (WS_SHOW_FLG == 'Y') {
                C100_OUTPUT_LIST();
                if (pgmRtn) return;
            }
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B101_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCSBCRD.IO_AREA.ID_TYP.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_TYPE;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
        if (DCCSBCRD.IO_AREA.ID_NO.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_ID_NO;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void B200_CHECK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDC);
        DCRCTCDC.NEW_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        WS_NEW_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCRCTCDC.CHG_STS = '2';
        T000_READ_DCTCTCDC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCTCDC.KEY.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCRCTCDC.HDOV_TLR);
        CEP.TRC(SCCGWA, DCRCTCDC.HDOV_DT);
        CEP.TRC(SCCGWA, DCRCTCDC.HDOV_BR);
        if (WS_TBL_FLAG == 'Y') {
            WS_SHOW_FLG = 'Y';
            WS_OLD_CARD_NO = DCRCTCDC.KEY.OLD_CARD_NO;
            WS_HDOV_TLR = DCRCTCDC.HDOV_TLR;
            WS_HDOV_DT = DCRCTCDC.HDOV_DT;
            WS_HDOV_BR = DCRCTCDC.HDOV_BR;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = WS_NEW_CARD_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            WS_SOCIAL_NO = DCRCITCD.SOCIAL_NO;
            WS_SOCIAL_CARD_NO = DCRCITCD.SOCIAL_CARD_NO;
            WS_E_CARD_NO = DCRCITCD.E_CARD_NO;
            IBS.init(SCCGWA, DCRCITCD);
            DCRCITCD.KEY.CARD_NO = WS_OLD_CARD_NO;
            T000_READ_DCTCITCD();
            if (pgmRtn) return;
            WS_OLD_SOCIAL_CARD_NO = DCRCITCD.SOCIAL_CARD_NO;
            WS_OLD_E_CARD_NO = DCRCITCD.E_CARD_NO;
        }
    }
    public void C000_OUTPUT_INI() throws IOException,SQLException,Exception {
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
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        WS_BROWSE_OUTPUT.WS_B_OLD_CARD_NO = WS_OLD_CARD_NO;
        WS_BROWSE_OUTPUT.WS_B_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_PROD_FLG == 'S') {
            WS_BROWSE_OUTPUT.WS_B_CITZEN_CRD_FLG = 'Y';
        } else {
            WS_BROWSE_OUTPUT.WS_B_CITZEN_CRD_FLG = 'N';
        }
        WS_BROWSE_OUTPUT.WS_B_HOLDER_IDTYPE = DCCSBCRD.IO_AREA.ID_TYP;
        WS_BROWSE_OUTPUT.WS_B_HOLDER_IDNO = DCCSBCRD.IO_AREA.ID_NO;
        WS_BROWSE_OUTPUT.WS_B_HOLDER_NAME = DCCSBCRD.IO_AREA.CI_NAME;
        WS_BROWSE_OUTPUT.WS_B_HDOV_TLR = WS_HDOV_TLR;
        WS_BROWSE_OUTPUT.WS_B_HDOV_DT = WS_HDOV_DT;
        WS_BROWSE_OUTPUT.WS_B_HDOV_BR = WS_HDOV_BR;
        WS_BROWSE_OUTPUT.WS_B_SOCIAL_NO = WS_SOCIAL_NO;
        WS_BROWSE_OUTPUT.WS_B_SOCIAL_CARD_NO = WS_SOCIAL_CARD_NO;
        WS_BROWSE_OUTPUT.WS_B_E_CARD_NO = WS_E_CARD_NO;
        WS_BROWSE_OUTPUT.WS_B_OLD_SOCIAL_CARD_NO = WS_OLD_SOCIAL_CARD_NO;
        WS_BROWSE_OUTPUT.WS_B_OLD_E_CARD_NO = WS_OLD_E_CARD_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 540;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SEARCH_CI, CICSSCH);
        if (CICSSCH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICSSCH.RC.RC_CODE);
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
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
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.col = "CARD_NO , EMBS_TYP , CRT_STS , APP_TELLER , APP_DT , APP_BR";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_MSG_ID);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.where = "NEW_CARD_NO = :DCRCTCDC.NEW_CARD_NO "
            + "AND CHG_STS = :DCRCTCDC.CHG_STS";
        IBS.READ(SCCGWA, DCRCTCDC, this, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void T000_READ_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITCD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.col = "CARD_NO , PROD_CD , CARD_CLS_PROD , EXC_CARD_TMS , CARD_HLDR_CINO, CARD_STS";
        DCTCDDAT_BR.rp.where = "CARD_HLDR_CINO = :DCRCDDAT.CARD_HLDR_CINO "
            + "AND CARD_STS = '3'";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCTCDDAT NOT FOUND1:");
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
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCTCDDAT NOT FOUND2:");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
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
