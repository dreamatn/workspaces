package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZLETIF {
    DBParm LNTCONT_RD;
    brParm LNTAGRE_BR = new brParm();
    int JIBS_tmp_int;
    DBParm LNTICTL_RD;
    DBParm LNTBALZ_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    LNZLETIF_WS_OUT_RECODE WS_OUT_RECODE = new LNZLETIF_WS_OUT_RECODE();
    LNZLETIF_WS_PAGE_INFO WS_PAGE_INFO = new LNZLETIF_WS_PAGE_INFO();
    LNZLETIF_WS_VARIABLES WS_VARIABLES = new LNZLETIF_WS_VARIABLES();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    CICQACR CICQACR = new CICQACR();
    CICCUST CICCUST = new CICCUST();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    CICQCIAC CICQCIAC = new CICQCIAC();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    LNCLETIF LNCLETIF;
    public void MP(SCCGWA SCCGWA, LNCLETIF LNCLETIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCLETIF = LNCLETIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZLETIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        IBS.init(SCCGWA, WS_PAGE_INFO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LNN01";
        LNCLETIF.RC.RC_MMO = "LN";
        LNCLETIF.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_GET_PAGE_INFO();
        if (pgmRtn) return;
        B300_FUNC_QUERY_CI_NO();
        if (pgmRtn) return;
        B800_OUTPUT_DATA_BEGIN();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCLETIF.PAPER_NO);
        if (LNCLETIF.PAPER_NO.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCLETIF.DUE_DT1 > LNCLETIF.DUE_DT2) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        WS_PAGE_INFO.ROW_FLG = 'N';
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD);
        if (LNCLETIF.DATA.PAGE_NUM == 0) {
            WS_PAGE_INFO.CURR_PAGE = 1;
        } else {
            WS_PAGE_INFO.CURR_PAGE = (short) LNCLETIF.DATA.PAGE_NUM;
        }
        WS_PAGE_INFO.LAST_PAGE = 'N';
        if (LNCLETIF.DATA.PAGE_ROW == 0) {
            WS_PAGE_INFO.PAGE_ROW = 25;
        } else {
            if (LNCLETIF.DATA.PAGE_ROW > 25) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, LNCLETIF.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_PAGE_INFO.PAGE_ROW = LNCLETIF.DATA.PAGE_ROW;
            }
        }
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
    }
    public void B300_FUNC_QUERY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.PAPER_NO = LNCLETIF.PAPER_NO;
        T000_STARTBR_AGRE();
        if (pgmRtn) return;
        WS_VARIABLES.WS_READ_TABLE_FLG.AGRE_FLAG = 'Y';
        T000_READNEXT_AGRE();
        if (pgmRtn) return;
        while (WS_VARIABLES.WS_READ_TABLE_FLG.AGRE_FLAG != 'N') {
            CEP.TRC(SCCGWA, LNRAGRE.DRAW_NO);
            CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
            if (LNCLETIF.STS != ' ') {
                T000_GET_LNTICTL01();
                if (pgmRtn) return;
            } else {
                T000_GET_LNTICTL02();
                if (pgmRtn) return;
            }
            if (LNCLETIF.LN_CCY.trim().length() == 0) {
                if (LNCLETIF.DUE_DT1 == 0 
                    && LNCLETIF.DUE_DT2 == 0) {
                    T000_GET_LNTCONT01();
                    if (pgmRtn) return;
                } else {
                    T000_GET_LNTCONT02();
                    if (pgmRtn) return;
                }
            } else {
                if (LNCLETIF.DUE_DT1 == 0 
                    && LNCLETIF.DUE_DT2 == 0) {
                    T000_GET_LNTCONT03();
                    if (pgmRtn) return;
                } else {
                    T000_GET_LNTCONT04();
                    if (pgmRtn) return;
                }
            }
            if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
                T000_GET_LNTBALZ();
                if (pgmRtn) return;
                R000_GET_CIZCUST();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
                R000_OUTPUT_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_AGRE();
            if (pgmRtn) return;
        }
        T000_ENDBR_AGRE();
        if (pgmRtn) return;
    }
    public void T000_GET_LNTCONT01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        T000_READ_LNTCONT01();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.START_DATE = LNRCONT.START_DATE;
            WS_VARIABLES.MAT_DATE = LNRCONT.MAT_DATE;
            WS_VARIABLES.PROD_CD = LNRCONT.PROD_CD;
            CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
            CEP.TRC(SCCGWA, LNRCONT.START_DATE);
            CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        }
    }
    public void T000_READ_LNTCONT01() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GET_LNTCONT04() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        LNRCONT.CCY = LNCLETIF.LN_CCY;
        LNRCONT.START_DATE = LNCLETIF.DUE_DT1;
        LNRCONT.MAT_DATE = LNCLETIF.DUE_DT2;
        T000_READ_LNTCONT04();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.START_DATE = LNRCONT.START_DATE;
            WS_VARIABLES.MAT_DATE = LNRCONT.MAT_DATE;
            WS_VARIABLES.PROD_CD = LNRCONT.PROD_CD;
            CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
            CEP.TRC(SCCGWA, LNRCONT.START_DATE);
            CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        }
    }
    public void T000_READ_LNTCONT04() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO "
            + "AND CCY = :LNRCONT.CCY "
            + "AND MAT_DATE >= :LNRCONT.START_DATE "
            + "AND MAT_DATE <= :LNRCONT.MAT_DATE";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GET_LNTCONT03() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        LNRCONT.CCY = LNCLETIF.LN_CCY;
        T000_READ_LNTCONT03();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.START_DATE = LNRCONT.START_DATE;
            WS_VARIABLES.MAT_DATE = LNRCONT.MAT_DATE;
            WS_VARIABLES.PROD_CD = LNRCONT.PROD_CD;
            CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
            CEP.TRC(SCCGWA, LNRCONT.START_DATE);
            CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        }
    }
    public void T000_READ_LNTCONT03() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO "
            + "AND CCY = :LNRCONT.CCY";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GET_LNTCONT02() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        LNRCONT.START_DATE = LNCLETIF.DUE_DT1;
        LNRCONT.MAT_DATE = LNCLETIF.DUE_DT2;
        T000_READ_LNTCONT02();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG == 'F') {
            WS_VARIABLES.START_DATE = LNRCONT.START_DATE;
            WS_VARIABLES.MAT_DATE = LNRCONT.MAT_DATE;
            WS_VARIABLES.PROD_CD = LNRCONT.PROD_CD;
            CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
            CEP.TRC(SCCGWA, LNRCONT.START_DATE);
            CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        }
    }
    public void T000_READ_LNTCONT02() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO "
            + "AND START_DATE >= :LNRCONT.START_DATE "
            + "AND MAT_DATE <= :LNRCONT.MAT_DATE";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTCONT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTCONT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_BR.rp = new DBParm();
        LNTAGRE_BR.rp.TableName = "LNTAGRE";
        LNTAGRE_BR.rp.where = "PAPER_NO = :LNRAGRE.PAPER_NO";
        IBS.STARTBR(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
    }
    public void T000_READNEXT_AGRE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRAGRE, this, LNTAGRE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.AGRE_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.AGRE_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAGRE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AGRE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTAGRE_BR);
    }
    public void T000_GET_LNTICTL02() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL02();
        if (pgmRtn) return;
        LNCLETIF.CTL_STSW = LNRICTL.CTL_STSW;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG == 'F') {
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(0, 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(11 - 1, 11 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(47 - 1, 47 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            if (LNCLETIF.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'A';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'P';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'M';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'D';
                }
            } else {
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'A';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'P';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'N';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'X';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'W';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'M';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'C';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'D';
                }
            }
            WS_VARIABLES.WS_D_DETAIL_DD.O_RATE = LNRICTL.CUR_RAT;
            CEP.TRC(SCCGWA, WS_VARIABLES.LETIF_STS);
            CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.O_RATE);
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        }
    }
    public void T000_READ_LNTICTL02() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_GET_LNTICTL01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL01();
        if (pgmRtn) return;
        LNCLETIF.CTL_STSW = LNRICTL.CTL_STSW;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG);
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG == 'F') {
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(0, 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(11 - 1, 11 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(2 - 1, 2 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNCLETIF.CTL_STSW.substring(47 - 1, 47 + 1 - 1));
            if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
            JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
            if (LNCLETIF.CTL_STSW.substring(71 - 1, 71 + 1 - 1).equalsIgnoreCase("1")) {
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'A';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'P';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'M';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'D';
                }
            } else {
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'A';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'P';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'N';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'X';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'W';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'M';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'C';
                }
                if (LNCLETIF.CTL_STSW == null) LNCLETIF.CTL_STSW = "";
                JIBS_tmp_int = LNCLETIF.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCLETIF.CTL_STSW += " ";
                if (LNCLETIF.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_VARIABLES.LETIF_STS = 'D';
                }
            }
            if (WS_VARIABLES.LETIF_STS == LNCLETIF.STS) {
                WS_VARIABLES.WS_D_DETAIL_DD.O_RATE = LNRICTL.CUR_RAT;
                CEP.TRC(SCCGWA, WS_VARIABLES.LETIF_STS);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.O_RATE);
                CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_STS, LNCLETIF.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTICTL01() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CIZCUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_VARIABLES.CI_NO = CICCUST.O_DATA.O_CI_NO;
        WS_VARIABLES.CI_NM = CICCUST.O_DATA.O_CI_NM;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_LOAN_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GET_LNTBALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNRAGRE.KEY.CONTRACT_NO;
        LNRBALZ.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTBALZ();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTBALZ_FLG == 'Y') {
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL01);
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL02);
        }
    }
    public void T000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTBALZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.WS_READ_TABLE_FLG.READ_LNTBALZ_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTBALZ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_D_DETAIL_DD.O_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.LETIF_STS);
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL01);
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL02);
        WS_PAGE_INFO.TOTAL_NUM += 1;
        if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
            && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
            WS_PAGE_INFO.ROW_FLG = 'Y';
            WS_PAGE_INFO.PAGE_IDX += 1;
            WS_PAGE_INFO.NEXT_START_NUM += 1;
            R000_WRITE_QUEUE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_QUEUE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1]);
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].PAPER_NO_OUT = LNCLETIF.PAPER_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].DRAW_NO_OUT = LNRAGRE.DRAW_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].CONTRACT_NO_OUT = LNRAGRE.KEY.CONTRACT_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].PROD_CD_OUT = LNRCONT.PROD_CD;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].START_DATE_OUT = LNRCONT.START_DATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].MAT_DATE_OUT = LNRCONT.MAT_DATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].CCY_OUT = LNRCONT.CCY;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].O_RATE_OUT = LNRICTL.CUR_PO_RAT;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].ALL_RATE_OUT = WS_VARIABLES.WS_D_DETAIL_DD.O_RATE;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].O_STS_OUT = WS_VARIABLES.LETIF_STS;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_PRIN_AMT_OUT = LNRBALZ.LOAN_BALL01;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].D_I_AMT_OUT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL06 + LNRBALZ.LOAN_BALL05;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].O_CI_NO = WS_VARIABLES.CI_NO;
        WS_OUT_RECODE.WS_OUT_INFO[WS_PAGE_INFO.PAGE_IDX-1].O_CI_NM = WS_VARIABLES.CI_NM;
    }
    public void B800_OUTPUT_DATA_BEGIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.BAL_CNT);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.ROW_FLG);
        if (WS_PAGE_INFO.ROW_FLG == 'Y') {
            WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
            WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
            if (WS_PAGE_INFO.BAL_CNT != 0) {
                WS_PAGE_INFO.TOTAL_PAGE += 1;
            }
            if (WS_PAGE_INFO.TOTAL_PAGE <= WS_PAGE_INFO.CURR_PAGE) {
                WS_PAGE_INFO.TOTAL_PAGE = WS_PAGE_INFO.CURR_PAGE;
                WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.PAGE_IDX;
                WS_PAGE_INFO.TOTAL_NUM = ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW + WS_PAGE_INFO.BAL_CNT;
                WS_PAGE_INFO.LAST_PAGE = 'Y';
                if (WS_PAGE_INFO.BAL_CNT != 0) {
                    WS_PAGE_INFO.PAGE_ROW = (short) WS_PAGE_INFO.BAL_CNT;
                }
            }
        } else {
            WS_PAGE_INFO.TOTAL_PAGE = 1;
            WS_PAGE_INFO.TOTAL_NUM = 0;
            WS_PAGE_INFO.LAST_PAGE = 'Y';
            WS_PAGE_INFO.PAGE_ROW = 0;
        }
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        if (WS_PAGE_INFO.TOTAL_NUM == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
        SCCFMT.FMTID = "LN814";
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 4479;
        IBS.FMT(SCCGWA, SCCFMT);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCLETIF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCLETIF=");
            CEP.TRC(SCCGWA, LNCLETIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
