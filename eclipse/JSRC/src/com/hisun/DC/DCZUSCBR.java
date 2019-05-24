package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUSCBR {
    DCZUSCBR_WS_OUT_INFO WS_OUT_INFO;
    int JIBS_tmp_int;
    brParm DCTCDDAT_BR = new brParm();
    DBParm DCTCDDAT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String DCZPQPRD = "DC-P-QUERY-PROD";
    String DCZUCINF = "DC-U-CARD-INF";
    String BPZSGSEQ = "BP-S-GET-SEQ";
    String PARM_READ = "BP-PARM-READ";
    String DCZUCDNO = "DC-U-CARDNO-GEN";
    int MAX_ROW = 99;
    int MAX_COL = 99;
    int COL_CNT = 7;
    String OUTPUT_FMT = "DC170";
    short Q_MAX_CNT = 1000;
    short PAGE_ROW = 25;
    DCZUSCBR_WS_VARIABLES WS_VARIABLES = new DCZUSCBR_WS_VARIABLES();
    DCZUSCBR_WS_OUT_RECODE WS_OUT_RECODE = new DCZUSCBR_WS_OUT_RECODE();
    DCZUSCBR_WS_COND_FLG WS_COND_FLG = new DCZUSCBR_WS_COND_FLG();
    DCCMSG_ERROR_MSG ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    CICCUST CICCUST = new CICCUST();
    CICCUST_WS_DB_VARS WS_DB_VARS = new CICCUST_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    DCCUSCBR DCCUSCBR;
    public void MP(SCCGWA SCCGWA, DCCUSCBR DCCUSCBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUSCBR = DCCUSCBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUSCBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_INFO_FILE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUSCBR.CARD_NO);
        if (DCCUSCBR.CARD_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUSCBR.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_COND_FLG.TBL_FLAG == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_LNK_TYP != '1') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_NOT_PRIM_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_INFO_FILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.PRIM_CARD_NO = DCCUSCBR.CARD_NO;
        DCRCDDAT.CARD_LNK_TYP = '2';
        DCRCDDAT.CARD_STS = 'C';
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            T000_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "---------------STARTBR--------------");
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            B021_OUTPUT_TITLE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            while (WS_COND_FLG.TBL_FLAG != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B022_OUTPUT_DETAIL();
                if (pgmRtn) return;
                T000_READNEXT_DCTCDDAT();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        } else {
            B023_OUTPUT_HEAD();
            if (pgmRtn) return;
            T000_STARTBR_DCTCDDAT_W();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDDAT_W();
            if (pgmRtn) return;
            if (WS_COND_FLG.TBL_FLAG == 'Y') {
                WS_VARIABLES.WS_DATA.IDX = 0;
                WS_VARIABLES.WS_DATA.TOTAL_NUM = 0;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.IDX);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.PAGE_ROW);
                while (WS_VARIABLES.WS_DATA.IDX != WS_VARIABLES.WS_DATA.PAGE_ROW 
                    && WS_COND_FLG.TBL_FLAG != 'N') {
                    B025_WRITE_OUTPUT();
                    if (pgmRtn) return;
                    WS_VARIABLES.WS_DATA.NEXT_START_NUM += 1;
                    WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
                    T000_READNEXT_DCTCDDAT_W();
                    if (pgmRtn) return;
                }
                if (WS_COND_FLG.TBL_FLAG == 'N') {
                    CEP.TRC(SCCGWA, "--------------T--------");
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
                    WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.IDX;
                    WS_VARIABLES.WS_DATA.TOTAL_NUM = ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW + WS_VARIABLES.WS_DATA.BAL_CNT;
                    WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                    WS_VARIABLES.WS_DATA.PAGE_ROW = (short) WS_VARIABLES.WS_DATA.BAL_CNT;
                    WS_OUT_INFO = new DCZUSCBR_WS_OUT_INFO();
                    WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
                } else {
                    CEP.TRC(SCCGWA, "--------------G--------");
                    DCRCDDAT.PRIM_CARD_NO = DCCUSCBR.CARD_NO;
                    DCRCDDAT.CARD_LNK_TYP = '2';
                    R000_GROUP_ALL();
                    if (pgmRtn) return;
                    WS_VARIABLES.WS_DATA.BAL_CNT = WS_VARIABLES.WS_DATA.TOTAL_NUM % WS_VARIABLES.WS_DATA.PAGE_ROW;
                    WS_VARIABLES.WS_DATA.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATA.TOTAL_NUM - WS_VARIABLES.WS_DATA.BAL_CNT) / WS_VARIABLES.WS_DATA.PAGE_ROW);
                    if (WS_VARIABLES.WS_DATA.BAL_CNT != 0) {
                        WS_VARIABLES.WS_DATA.TOTAL_PAGE += 1;
                    }
                }
            } else {
                WS_VARIABLES.WS_DATA.TOTAL_PAGE = 1;
                WS_VARIABLES.WS_DATA.TOTAL_NUM = 0;
                WS_VARIABLES.WS_DATA.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATA.PAGE_ROW = 0;
                WS_OUT_INFO = new DCZUSCBR_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        T000_ENDBR_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "-----------------ENDBR--------------");
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATA.TOTAL_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATA.TOTAL_NUM;
            WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATA.LAST_PAGE;
            WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_VARIABLES.WS_DATA.PAGE_ROW;
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
            B024_OUTPUT_WRITE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "-----------------ENDBR--------------");
    }
    public void B021_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES.WS_OUTPUT);
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        WS_VARIABLES.WS_OUTPUT.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        WS_VARIABLES.WS_OUTPUT.HOLDER_CINO = DCRCDDAT.CARD_HLDR_CINO;
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_VARIABLES.WS_OUTPUT.HOLDER_IDTYPE = CICCUST.O_DATA.O_ID_TYPE;
            WS_VARIABLES.WS_OUTPUT.HOLDER_IDNO = CICCUST.O_DATA.O_ID_NO;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() == 0) {
                WS_VARIABLES.WS_OUTPUT.HOLDER_CINM = CICCUST.O_DATA.O_CI_ENM;
            } else {
                WS_VARIABLES.WS_OUTPUT.HOLDER_CINM = CICCUST.O_DATA.O_CI_NM;
            }
        }
        WS_VARIABLES.WS_OUTPUT.CARD_STS = DCRCDDAT.CARD_STS;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_OUTPUT.ORA_LOS_FLG = 'Y';
        } else {
            WS_VARIABLES.WS_OUTPUT.ORA_LOS_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_OUTPUT.OFF_LOS_FLG = 'Y';
        } else {
            WS_VARIABLES.WS_OUTPUT.OFF_LOS_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.WS_OUTPUT.SWA_CARD_FLG = 'Y';
        } else {
            WS_VARIABLES.WS_OUTPUT.SWA_CARD_FLG = 'N';
        }
        WS_VARIABLES.WS_OUTPUT.OPEN_DT = DCRCDDAT.ISSU_DT;
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_OUTPUT);
        SCCMPAG.DATA_LEN = 372;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B023_OUTPUT_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (DCCUSCBR.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATA.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATA.CURR_PAGE = (short) DCCUSCBR.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATA.CURR_PAGE;
        WS_VARIABLES.WS_DATA.LAST_PAGE = 'N';
        if (DCCUSCBR.PAGE_ROW == 0) {
            WS_VARIABLES.WS_DATA.PAGE_ROW = PAGE_ROW;
            WS_OUT_INFO = new DCZUSCBR_WS_OUT_INFO();
            WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
        } else {
            if (DCCUSCBR.PAGE_ROW > PAGE_ROW) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.DC_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.WS_DATA.PAGE_ROW = (short) DCCUSCBR.PAGE_ROW;
                WS_OUT_INFO = new DCZUSCBR_WS_OUT_INFO();
                WS_OUT_RECODE.WS_OUT_INFO.add(WS_OUT_INFO);
            }
        }
        WS_VARIABLES.WS_DATA.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATA.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATA.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATA.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
    }
    public void B024_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "DC313";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 9319;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B025_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_DATA.IDX += 1;
        IBS.init(SCCGWA, WS_OUT_INFO);
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        WS_OUT_INFO.O_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        WS_OUT_INFO.O_HOLDER_CINO = DCRCDDAT.CARD_HLDR_CINO;
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_OUT_INFO.O_HOLDER_IDTYPE = CICCUST.O_DATA.O_ID_TYPE;
            WS_OUT_INFO.O_HOLDER_IDNO = CICCUST.O_DATA.O_ID_NO;
            if (CICCUST.O_DATA.O_CI_NM.trim().length() == 0) {
                WS_OUT_INFO.O_HOLDER_CINM = CICCUST.O_DATA.O_CI_ENM;
            } else {
                WS_OUT_INFO.O_HOLDER_CINM = CICCUST.O_DATA.O_CI_NM;
            }
        }
        WS_OUT_INFO.O_CARD_STS = DCRCDDAT.CARD_STS;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_OUT_INFO.O_ORA_LOS_FLG = 'Y';
        } else {
            WS_OUT_INFO.O_ORA_LOS_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUT_INFO.O_OFF_LOS_FLG = 'Y';
        } else {
            WS_OUT_INFO.O_OFF_LOS_FLG = 'N';
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_OUT_INFO.O_SWA_CARD_FLG = 'Y';
        } else {
            WS_OUT_INFO.O_SWA_CARD_FLG = 'N';
        }
        WS_OUT_INFO.O_OPEN_DT = DCRCDDAT.ISSU_DT;
        CEP.TRC(SCCGWA, "--------OUTPUT---------");
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "( PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO ) "
            + "AND ( CARD_LNK_TYP = :DCRCDDAT.CARD_LNK_TYP ) "
            + "AND ( CARD_STS < > :DCRCDDAT.CARD_STS )";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_STARTBR_DCTCDDAT_W() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "( PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO ) "
            + "AND ( CARD_LNK_TYP = :DCRCDDAT.CARD_LNK_TYP )";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTCDDAT_W() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else {
            WS_COND_FLG.TBL_FLAG = 'N';
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.set = "WS-NUM=COUNT(*)";
        DCTCDDAT_RD.where = "( PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO ) "
            + "AND ( CARD_LNK_TYP = :DCRCDDAT.CARD_LNK_TYP )";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
        WS_VARIABLES.WS_DATA.TOTAL_NUM = WS_DB_VARS.NUM;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATA.TOTAL_NUM);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
