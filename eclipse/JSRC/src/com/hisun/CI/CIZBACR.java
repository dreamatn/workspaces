package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBACR {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    brParm CITACR_BR = new brParm();
    DBParm CITACR_RD;
    DBParm CITACRL_RD;
    DBParm CITNAM_RD;
    brParm CITJRL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_CI_NO = " ";
    char WS_ACR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    CIRNAM CIRNAM = new CIRNAM();
    CIRJRL CIRJRL = new CIRJRL();
    CICOACRL CICOACRL = new CICOACRL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICQACRI CICQACRI = new CICQACRI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBACR CICBACR;
    public void MP(SCCGWA SCCGWA, CICBACR CICBACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBACR = CICBACR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBACR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBACR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICBACR.DATA.AGR_NO.trim().length() == 0) {
            B020_BROWSE_ACR_INF();
            if (pgmRtn) return;
        } else {
            B030_INQUIRE_ACR_INF();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_ACR_INF() throws IOException,SQLException,Exception {
        WS_ACR_FLG = 'N';
        CEP.TRC(SCCGWA, CICBACR.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICBACR.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICBACR.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICBACR.DATA.CI_NM);
        CEP.TRC(SCCGWA, CICBACR.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICBACR.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICBACR.DATA.STS);
        if (CICBACR.DATA.CI_NO.trim().length() > 0) {
            B021_BROWSE_BY_CI_NO();
            if (pgmRtn) return;
        } else if (CICBACR.DATA.CI_NM.trim().length() > 0 
                && CICBACR.DATA.ID_TYPE.trim().length() > 0 
                && CICBACR.DATA.ID_NO.trim().length() > 0) {
            B022_BROWSE_BY_IDNM();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICBACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            if ((CICBACR.DATA.FRM_APP.trim().length() == 0 
                || CICBACR.DATA.FRM_APP.equalsIgnoreCase(CIRACR.FRM_APP)) 
                && (CICBACR.DATA.OPN_BR == 0 
                || CICBACR.DATA.OPN_BR == CIRACR.OPN_BR) 
                && (CICBACR.DATA.ENTY_TYP == ' ' 
                || CICBACR.DATA.ENTY_TYP == CIRACR.ENTY_TYP 
                || (CICBACR.DATA.ENTY_TYP == '5' 
                && CIRACR.ENTY_TYP == '6'))) {
                IBS.init(SCCGWA, BPCPQPRD);
                BPCPQPRD.PRDT_CODE = CIRACR.PROD_CD;
                S000_CALL_BPZPQPRD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.HCI_NO = WS_CI_NO;
        T000_STARTBR_CITJRL_BY_HCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.CI_NO = CIRJRL.KEY.JCI_NO;
            T000_STARTBR_CITACR_CINO();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && SCCMPAG.FUNC != 'E') {
                if ((CICBACR.DATA.FRM_APP.trim().length() == 0 
                    || CICBACR.DATA.FRM_APP.equalsIgnoreCase(CIRACR.FRM_APP)) 
                    && (CICBACR.DATA.OPN_BR == 0 
                    || CICBACR.DATA.OPN_BR == CIRACR.OPN_BR) 
                    && (CICBACR.DATA.ENTY_TYP == ' ' 
                    || CICBACR.DATA.ENTY_TYP == CIRACR.ENTY_TYP 
                    || (CICBACR.DATA.ENTY_TYP == '5' 
                    && CIRACR.ENTY_TYP == '6'))) {
                    IBS.init(SCCGWA, BPCPQPRD);
                    BPCPQPRD.PRDT_CODE = CIRACR.PROD_CD;
                    S000_CALL_BPZPQPRD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                    R000_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITACR();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITACR();
            if (pgmRtn) return;
            T000_READNEXT_CITJRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITJRL();
        if (pgmRtn) return;
        if (WS_ACR_FLG == 'N') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICBACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_BROWSE_BY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICBACR.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        WS_CI_NO = CIRBAS.KEY.CI_NO;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICBACR.DATA.CI_NO;
        T000_STARTBR_CITACR_CINO();
        if (pgmRtn) return;
    }
    public void B022_BROWSE_BY_IDNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.CI_NM = CICBACR.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_NO = CICBACR.DATA.ID_NO;
        CIRBAS.ID_TYPE = CICBACR.DATA.ID_TYPE;
        T000_READ_CITBAS_IDNM();
        if (pgmRtn) return;
        WS_CI_NO = CIRBAS.KEY.CI_NO;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITACR_CINO();
        if (pgmRtn) return;
    }
    public void B030_INQUIRE_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = CICBACR.DATA.AGR_NO;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_PROD_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = CICQACRI.O_DATA.O_PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
        } else {
            BPCPQPRD.PRDT_NAME = " ";
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_03_OUT_ACR_INF();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        WS_ACR_FLG = 'F';
        IBS.init(SCCGWA, CICOACRL);
        CICOACRL.DATA.ENTY_NO = CIRACR.KEY.AGR_NO;
        CICOACRL.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOACRL.DATA.PROD_CD = CIRACR.PROD_CD;
        CICOACRL.DATA.CDESC = BPCPQPRD.PRDT_NAME;
        CICOACRL.DATA.CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICOACRL.DATA.FRM_APP = CIRACR.FRM_APP;
        CICOACRL.DATA.STS = CIRACR.STS;
        CICOACRL.DATA.CCY = CIRACR.CCY;
        CICOACRL.DATA.OPN_BR = CIRACR.OPN_BR;
        CICOACRL.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOACRL.DATA.CI_NO = CIRACR.CI_NO;
        CICOACRL.DATA.AC_CNM = CIRACR.AC_CNM;
        CICOACRL.DATA.AC_ENM = CIRACR.AC_ENM;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOACRL.DATA.CI_TYPE = CIRBAS.CI_TYP;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRACR.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (CIRBAS.CI_TYP == '1') {
            if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
            JIBS_tmp_int = CIRBAS.CI_NM.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
            CICOACRL.DATA.AC_CNM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CICOACRL.DATA.AC_ENM = CIRNAM.CI_NM;
            }
        } else if (CIRBAS.CI_TYP == '2' 
                && CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            CIRACRL.KEY.AC_REL = "04";
            T000_READ_CITACRL_BY_ACNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND);
            }
            CICOACRL.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOACRL.DATA.AC_ENM = CIRACR.AC_ENM;
        } else {
            CICOACRL.DATA.AC_CNM = CIRACR.AC_CNM;
            CICOACRL.DATA.AC_ENM = CIRACR.AC_ENM;
        }
        if (CIRACR.FRM_APP.equalsIgnoreCase("DD") 
            && CIRBAS.CI_TYP != '1') {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
            if (DDCIMMST.DATA.AC_TYPE == 'P') {
                CICOACRL.DATA.VER_FLG = 'Y';
            }
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOACRL);
        SCCMPAG.DATA_LEN = 654;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_03_OUT_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOACRL);
        CICOACRL.DATA.ENTY_NO = CICQACRI.O_DATA.O_AGR_NO;
        CICOACRL.DATA.ENTY_TYP = CICQACRI.O_DATA.O_ENTY_TYP;
        CICOACRL.DATA.PROD_CD = CICQACRI.O_DATA.O_PROD_CD;
        CICOACRL.DATA.CDESC = BPCPQPRD.PRDT_NAME;
        CICOACRL.DATA.CNTRCT_TYP = CICQACRI.O_DATA.O_CNTRCT_TYP;
        CICOACRL.DATA.FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
        CICOACRL.DATA.STS = CICQACRI.O_DATA.O_STS;
        CICOACRL.DATA.CCY = CICQACRI.O_DATA.O_CCY;
        CICOACRL.DATA.OPN_BR = CICQACRI.O_DATA.O_OPN_BR;
        CICOACRL.DATA.OPEN_DT = CICQACRI.O_DATA.O_OPEN_DT;
        CICOACRL.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        CICOACRL.DATA.AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
        CICOACRL.DATA.AC_ENM = CICQACRI.O_DATA.O_AC_ENM;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CICOACRL.DATA.CI_TYPE = CIRBAS.CI_TYP;
        if (CIRACR.FRM_APP.equalsIgnoreCase("DD") 
            && CIRBAS.CI_TYP != '1') {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
            if (DDCIMMST.DATA.AC_TYPE == 'P') {
                CICOACRL.DATA.VER_FLG = 'Y';
            }
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOACRL);
        SCCMPAG.DATA_LEN = 654;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST, true);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICBACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_NO , ID_TYPE , CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BAS INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICBACR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITACR_CINO() throws IOException,SQLException,Exception {
        if (CICBACR.DATA.STS == '0') {
            CITACR_BR.rp = new DBParm();
            CITACR_BR.rp.TableName = "CITACR";
            CITACR_BR.rp.eqWhere = "CI_NO";
            CITACR_BR.rp.where = "STS = '0' "
                + "AND SHOW_FLG = 'Y'";
            CITACR_BR.rp.order = "OPEN_DT DESC";
            IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
        } else if (CICBACR.DATA.STS == '1') {
            CITACR_BR.rp = new DBParm();
            CITACR_BR.rp.TableName = "CITACR";
            CITACR_BR.rp.eqWhere = "CI_NO";
            CITACR_BR.rp.where = "STS = '1' "
                + "AND SHOW_FLG = 'Y'";
            CITACR_BR.rp.order = "OPEN_DT DESC";
            IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
        } else if (CICBACR.DATA.STS == 'A') {
            CITACR_BR.rp = new DBParm();
            CITACR_BR.rp.TableName = "CITACR";
            CITACR_BR.rp.eqWhere = "CI_NO";
            CITACR_BR.rp.where = "SHOW_FLG = 'Y'";
            CITACR_BR.rp.order = "OPEN_DT DESC";
            IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
        } else {
            CEP.TRC(SCCGWA, "INVALID STATUS INPUT ");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICBACR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACRL_BY_ACNO() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.col = "CI_NM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_STARTBR_CITJRL_BY_HCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp = new DBParm();
        CITJRL_BR.rp.TableName = "CITJRL";
        CITJRL_BR.rp.eqWhere = "HCI_NO";
        IBS.STARTBR(SCCGWA, CIRJRL, CITJRL_BR);
    }
    public void T000_READNEXT_CITJRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRJRL, this, CITJRL_BR);
    }
    public void T000_ENDBR_CITJRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITJRL_BR);
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
