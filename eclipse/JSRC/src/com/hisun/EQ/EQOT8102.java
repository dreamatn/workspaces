package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8102 {
    DBParm EQTACT_RD;
    DBParm EQTBVT_RD;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ACT_FLG = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    EQRACT EQRACT = new EQRACT();
    EQRBVT EQRBVT = new EQRBVT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    EQCSACT EQCSACT = new EQCSACT();
    SCCGWA SCCGWA;
    EQB1100_AWA_1100 EQB1100_AWA_1100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "EQOT8102 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB1100_AWA_1100>");
        EQB1100_AWA_1100 = (EQB1100_AWA_1100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        CEP.TRC(SCCGWA, "AUTHHHHHH");
        B020_GET_INF();
        B030_MAIN_PROC();
        EQB1100_AWA_1100.EQ_AC = EQCSACT.DATA.EQ_AC;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_BKID);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.PROD_CD);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.PROD_NM);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.CI_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_CNM);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.ID_TYP);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.ID_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.TEL_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_ADDR);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.CI_NAME);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.ID_NUM);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.ADD_BR);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_AC);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.PSBK_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_ACT);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_CINO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_QTY);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.FRZ_QTY);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.PLG_QTY);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.FD_SRC);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.PAY_VCH);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.PAY_AC);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.DRAW_MTH);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.BR_AC);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.CREV_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.BLL_KND);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.BLL_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.SIGN_DT);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EXP_QTY);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EXP_PRI);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EXP_AMT);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.DIV_AC);
        if (EQB1100_AWA_1100.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            WS_FLD_NO = EQB1100_AWA_1100.EQ_BKID_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (EQB1100_AWA_1100.EQ_AC.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_MUST_INPUT;
            WS_FLD_NO = EQB1100_AWA_1100.EQ_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (EQB1100_AWA_1100.FD_SRC == ' ') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_FROM_TYP_MUST_INPUT;
            WS_FLD_NO = EQB1100_AWA_1100.FD_SRC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (EQB1100_AWA_1100.EXP_QTY == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EXP_QUANTITY_MUST_INPUT;
            WS_FLD_NO = EQB1100_AWA_1100.EXP_QTY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            if (EQB1100_AWA_1100.EXP_QTY <= 0) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EXP_QUANTITY_INVALID;
                WS_FLD_NO = EQB1100_AWA_1100.EXP_QTY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (EQB1100_AWA_1100.EXP_PRI == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EXP_PRICE_MUST_INPUT;
            WS_FLD_NO = EQB1100_AWA_1100.EXP_PRI_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        } else {
            if (EQB1100_AWA_1100.EXP_PRI <= 0) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EXP_PRICE_INVALID;
                WS_FLD_NO = EQB1100_AWA_1100.EXP_PRI_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_GET_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQB1100_AWA_1100.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQB1100_AWA_1100.EQ_AC;
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        T000_READ_EQTACT();
        if (WS_ACT_FLG == 'N') {
            IBS.init(SCCGWA, EQRACT);
            WS_ACT_FLG = ' ';
            EQRACT.KEY.EQ_AC = EQB1100_AWA_1100.EQ_AC;
            T000_READ_EQTACT2();
            if (WS_ACT_FLG == 'N') {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BKID_ERROR;
                S000_ERR_MSG_PROC();
            }
        } else {
            EQB1100_AWA_1100.CI_NO = EQRACT.CI_NO;
            EQB1100_AWA_1100.PROD_CD = EQRACT.PROD_CD;
            EQB1100_AWA_1100.ADD_BR = EQRACT.OWNER_BK;
            EQB1100_AWA_1100.EQ_ACT = EQRACT.EQ_ACT;
            EQB1100_AWA_1100.EQ_CINO = EQRACT.EQ_CINO;
            EQB1100_AWA_1100.EQ_QTY = EQRACT.EQ_QTY;
            EQB1100_AWA_1100.FRZ_QTY = EQRACT.FRZ_QTY;
            EQB1100_AWA_1100.PLG_QTY = EQRACT.PLG_QTY;
            EQB1100_AWA_1100.DIV_AC = EQRACT.DIV_AC;
            if (!EQB1100_AWA_1100.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
                IBS.init(SCCGWA, EQRBVT);
                EQRBVT.KEY.EQ_BKID = EQB1100_AWA_1100.EQ_BKID;
                EQRBVT.KEY.EQ_AC = EQB1100_AWA_1100.EQ_AC;
                T000_READ_EQTBVT();
                EQB1100_AWA_1100.PSBK_NO = EQRBVT.KEY.PSBK_NO;
            }
        }
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCSACT);
        EQCSACT.FUNC = 'E';
        EQCSACT.DATA.EQ_BKID = EQB1100_AWA_1100.EQ_BKID;
        EQCSACT.DATA.PROD_CD = EQB1100_AWA_1100.PROD_CD;
        EQCSACT.DATA.CI_NO = EQB1100_AWA_1100.CI_NO;
        EQCSACT.DATA.ADD_BR = EQB1100_AWA_1100.ADD_BR;
        EQCSACT.DATA.EQ_AC = EQB1100_AWA_1100.EQ_AC;
        EQCSACT.DATA.PSBK_NO = EQB1100_AWA_1100.PSBK_NO;
        EQCSACT.DATA.EQ_ACT = EQB1100_AWA_1100.EQ_ACT;
        EQCSACT.DATA.EQ_CINO = EQB1100_AWA_1100.EQ_CINO;
        EQCSACT.DATA.EQ_QTY = EQB1100_AWA_1100.EQ_QTY;
        EQCSACT.DATA.FRZ_QTY = EQB1100_AWA_1100.FRZ_QTY;
        EQCSACT.DATA.PLG_QTY = EQB1100_AWA_1100.PLG_QTY;
        EQCSACT.DATA.FD_SRC = EQB1100_AWA_1100.FD_SRC;
        EQCSACT.DATA.PAY_VCH = EQB1100_AWA_1100.PAY_VCH;
        EQCSACT.DATA.PAY_AC = EQB1100_AWA_1100.PAY_AC;
        EQCSACT.DATA.DRAW_MTH = EQB1100_AWA_1100.DRAW_MTH;
        EQCSACT.DATA.DRAW_PSW = EQB1100_AWA_1100.DRAW_PSW;
        EQCSACT.DATA.BR_AC = EQB1100_AWA_1100.BR_AC;
        EQCSACT.DATA.CREV_NO = EQB1100_AWA_1100.CREV_NO;
        EQCSACT.DATA.BLL_KND = EQB1100_AWA_1100.BLL_KND;
        EQCSACT.DATA.BLL_NO = EQB1100_AWA_1100.BLL_NO;
        EQCSACT.DATA.BLL_PSW = EQB1100_AWA_1100.BLL_PSW;
        EQCSACT.DATA.SIGN_DT = EQB1100_AWA_1100.SIGN_DT;
        EQCSACT.DATA.EXP_QTY = EQB1100_AWA_1100.EXP_QTY;
        EQCSACT.DATA.EXP_PRI = EQB1100_AWA_1100.EXP_PRI;
        EQCSACT.DATA.EXP_AMT = EQB1100_AWA_1100.EXP_AMT;
        EQCSACT.DATA.DIV_AC = EQB1100_AWA_1100.DIV_AC;
        CEP.TRC(SCCGWA, "8102AAA");
        S000_CALL_EQZSACT();
    }
    public void S000_CALL_EQZSACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-SACT", EQCSACT);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT2() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_AC = :EQRACT.KEY.EQ_AC";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_STS = 'N'";
        EQTBVT_RD.fst = true;
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND;
            S000_ERR_MSG_PROC();
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
