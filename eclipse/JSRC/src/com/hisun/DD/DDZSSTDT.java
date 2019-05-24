package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSSTDT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTACHK_RD;
    DBParm DDTMST_RD;
    brParm DDTACHK_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD570";
    String K_OUTPUT_FMT1 = "DD571";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    DDZSSTDT_WS_THIS_DATE WS_THIS_DATE = new DDZSSTDT_WS_THIS_DATE();
    DDZSSTDT_WS_OUTPUT_DATA WS_OUTPUT_DATA = new DDZSSTDT_WS_OUTPUT_DATA();
    DDZSSTDT_WS_OUTPUT_DATA1 WS_OUTPUT_DATA1 = new DDZSSTDT_WS_OUTPUT_DATA1();
    char WS_ACHK_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDRACHK DDRACHK = new DDRACHK();
    DDRMST DDRMST = new DDRMST();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DDCSSTDT DDCSSTDT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSSTDT DDCSSTDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSSTDT = DDCSSTDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSSTDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DDCSSTDT.FUNC == 'A') {
            B010_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSSTDT.FUNC == 'B') {
            B020_BRW_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSSTDT.FUNC == 'C') {
            B030_QRY_ACNO_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSSTDT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_ADD_PROCESS() throws IOException,SQLException,Exception {
        B010_10_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        T000_ADD_DDTACHK();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_10_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DDCSSTDT.BR == 0) {
            DDCSSTDT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (DDCSSTDT.CHK_YEAR == 0) {
            IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AC_DATE+"", WS_THIS_DATE);
            DDCSSTDT.CHK_YEAR = WS_THIS_DATE.WS_THIS_YEAR;
        }
        if (DDCSSTDT.STR_DATE < SCCGWA.COMM_AREA.AC_DATE 
            || DDCSSTDT.STR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DATE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSSTDT.END_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSSTDT.END_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = "" + SCCCKDT.RC;
                JIBS_tmp_int = WS_ERR_MSG.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSSTDT.END_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATE_LT_ACDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSSTDT.END_DATE < DDCSSTDT.STR_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_YCHK_STRDT_LT_ENDDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_PROCESS() throws IOException,SQLException,Exception {
        B020_10_OPEN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B020_30_CHECK_DATA_BROWSE();
        if (pgmRtn) return;
    }
    public void B030_QRY_ACNO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA1);
        B030_10_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 2 - 1).equalsIgnoreCase("00")) {
            WS_OUTPUT_DATA1.WS_OLD_CHK_STS = '3';
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_OUTPUT_DATA1.WS_CHK_YR = 0;
            else WS_OUTPUT_DATA1.WS_CHK_YR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("0")) {
            WS_OUTPUT_DATA1.WS_OLD_CHK_STS = '1';
            WS_OUTPUT_DATA1.WS_CHK_YR = 0;
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("0") 
            && DDRMST.AC_STS_WORD.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUTPUT_DATA1.WS_OLD_CHK_STS = '2';
            WS_OUTPUT_DATA1.WS_CHK_YR = 0;
        }
        R000_OUTPUT_DATA1();
        if (pgmRtn) return;
    }
    public void B020_10_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 147;
        if ("50".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("50");
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_30_CHECK_DATA_BROWSE() throws IOException,SQLException,Exception {
        if (DDCSSTDT.BR != 0 
            && DDCSSTDT.CHK_YEAR != 0) {
            CEP.TRC(SCCGWA, "NOT SPACE");
            CEP.TRC(SCCGWA, DDCSSTDT.BR);
            CEP.TRC(SCCGWA, DDCSSTDT.CHK_YEAR);
            T000_READ_DDTACHK();
            if (pgmRtn) return;
            R000_TRANS_DATA();
            if (pgmRtn) return;
        }
        if (DDCSSTDT.BR != 0 
            && DDCSSTDT.CHK_YEAR == 0) {
            CEP.TRC(SCCGWA, DDCSSTDT.BR);
            T000_STARTBR_BR_DDTACHK();
            if (pgmRtn) return;
            T000_READ_NEXT_DDTACHK();
            if (pgmRtn) return;
            while (WS_ACHK_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                R000_TRANS_DATA();
                if (pgmRtn) return;
                T000_READ_NEXT_DDTACHK();
                if (pgmRtn) return;
            }
            T000_END_BROWSE_PROC();
            if (pgmRtn) return;
        }
        if (DDCSSTDT.CHK_YEAR != 0 
            && DDCSSTDT.BR == 0) {
            CEP.TRC(SCCGWA, DDCSSTDT.CHK_YEAR);
            T000_STARTBR_YR_DDTACHK();
            if (pgmRtn) return;
            T000_READ_NEXT_DDTACHK();
            if (pgmRtn) return;
            while (WS_ACHK_FLG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                R000_TRANS_DATA();
                if (pgmRtn) return;
                T000_READ_NEXT_DDTACHK();
                if (pgmRtn) return;
            }
            T000_END_BROWSE_PROC();
            if (pgmRtn) return;
        }
        if (DDCSSTDT.BR == 0 
            && DDCSSTDT.CHK_YEAR == 0) {
            DDCSSTDT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AC_DATE+"", WS_THIS_DATE);
            DDCSSTDT.CHK_YEAR = WS_THIS_DATE.WS_THIS_YEAR;
            CEP.TRC(SCCGWA, "SPACE SPACE");
            CEP.TRC(SCCGWA, DDCSSTDT.BR);
            CEP.TRC(SCCGWA, DDCSSTDT.CHK_YEAR);
            T000_READ_DDTACHK();
            if (pgmRtn) return;
            R000_TRANS_DATA();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_BR = DDCSSTDT.BR;
        WS_OUTPUT_DATA.WS_CHK_YEAR = DDCSSTDT.CHK_YEAR;
        WS_OUTPUT_DATA.WS_STR_DATE = DDCSSTDT.STR_DATE;
        WS_OUTPUT_DATA.WS_END_DATE = DDCSSTDT.END_DATE;
        WS_OUTPUT_DATA.WS_RMK = DDCSSTDT.RMK;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 147;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OUTPUT_DATA1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA1;
        SCCFMT.DATA_LEN = 5;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FUND");
            WS_OUTPUT_DATA.WS_BR = DDRACHK.KEY.BR;
            WS_OUTPUT_DATA.WS_CHK_YEAR = DDRACHK.KEY.CHK_YEAR;
            WS_OUTPUT_DATA.WS_STR_DATE = DDRACHK.STR_DATE;
            WS_OUTPUT_DATA.WS_END_DATE = DDRACHK.END_DATE;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 147;
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void T000_ADD_DDTACHK() throws IOException,SQLException,Exception {
        DDRACHK.KEY.BR = DDCSSTDT.BR;
        DDRACHK.KEY.CHK_YEAR = DDCSSTDT.CHK_YEAR;
        DDRACHK.STR_DATE = DDCSSTDT.STR_DATE;
        DDRACHK.END_DATE = DDCSSTDT.END_DATE;
        DDRACHK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRACHK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRACHK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRACHK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDTACHK_RD = new DBParm();
        DDTACHK_RD.TableName = "DDTACHK";
        DDTACHK_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRACHK, DDTACHK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHK_DATE_SET;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_10_READ_DDTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSSTDT.AC_NO;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'O') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_ACTV;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.CI_TYP != '2' 
            && DDRMST.CI_TYP != '3') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.CI_TYP_NOT_MATCH_MPRD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_BR_DDTACHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACHK);
        DDRACHK.KEY.BR = DDCSSTDT.BR;
        WS_ACHK_FLG = 'N';
        DDTACHK_BR.rp = new DBParm();
        DDTACHK_BR.rp.TableName = "DDTACHK";
        DDTACHK_BR.rp.where = "BR = :DDRACHK.KEY.BR";
        DDTACHK_BR.rp.order = "CHK_YEAR";
        IBS.STARTBR(SCCGWA, DDRACHK, this, DDTACHK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_YR_DDTACHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACHK);
        DDRACHK.KEY.CHK_YEAR = DDCSSTDT.CHK_YEAR;
        WS_ACHK_FLG = 'N';
        DDTACHK_BR.rp = new DBParm();
        DDTACHK_BR.rp.TableName = "DDTACHK";
        DDTACHK_BR.rp.where = "CHK_YEAR = :DDRACHK.KEY.CHK_YEAR";
        DDTACHK_BR.rp.order = "BR";
        IBS.STARTBR(SCCGWA, DDRACHK, this, DDTACHK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_NEXT_DDTACHK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRACHK, this, DDTACHK_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACHK_FLG = 'Y';
        } else {
            WS_ACHK_FLG = 'N';
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTACHK_BR);
    }
    public void T000_READ_DDTACHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRACHK);
        DDRACHK.KEY.BR = DDCSSTDT.BR;
        DDRACHK.KEY.CHK_YEAR = DDCSSTDT.CHK_YEAR;
        CEP.TRC(SCCGWA, "READREAD");
        DDTACHK_RD = new DBParm();
        DDTACHK_RD.TableName = "DDTACHK";
        IBS.READ(SCCGWA, DDRACHK, DDTACHK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
