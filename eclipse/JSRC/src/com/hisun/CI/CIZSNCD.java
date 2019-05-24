package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.BP.*;
import com.hisun.TD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSNCD {
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITACAC_RD;
    brParm CITACRL_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 5;
    String K_PERSON_NCD = "6311350000";
    String K_COMPANY_NCD = "6312080000";
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    String WS_PROD_CD = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    CIRAGT CIRAGT = new CIRAGT();
    TDCACE TDCACE = new TDCACE();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICFA06 CICFA06 = new CICFA06();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSNCD CICSNCD;
    public void MP(SCCGWA SCCGWA, CICSNCD CICSNCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSNCD = CICSNCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSNCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSNCD.RC);
        IBS.init(SCCGWA, CICFA06);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_COMPUTE_OUTPUT_ROW();
        if (pgmRtn) return;
        if (CICSNCD.DATA.AGR_NO.trim().length() > 0) {
            B030_INQ_NCD_INF_BY_AC();
            if (pgmRtn) return;
        } else {
            B040_INQ_NCD_INF_BY_CI();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICSNCD.DATA.PROD_CD.equalsIgnoreCase(K_PERSON_NCD) 
            || CICSNCD.DATA.PROD_CD.equalsIgnoreCase(K_COMPANY_NCD)) {
            WS_PROD_CD = CICSNCD.DATA.PROD_CD;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_PROD_CD_INPUT_ERR);
        }
        CEP.TRC(SCCGWA, CICSNCD.DATA.PAGE_ROW);
        if (CICSNCD.DATA.PAGE_ROW > K_MAX_ROW) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICSNCD.DATA.PAGE_ROW;
        }
    }
    public void B020_COMPUTE_OUTPUT_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSNCD.DATA.PAGE_NUM);
        WS_MIN_ROW = ( CICSNCD.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_MIN_ROW);
        WS_MAX_ROW = CICSNCD.DATA.PAGE_NUM * WS_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_MAX_ROW);
        WS_CURRENT_ROW = 1;
        WS_I = 1;
    }
    public void B030_INQ_NCD_INF_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSNCD.DATA.AGR_NO);
        if (CICSNCD.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSNCD.DATA.AGR_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIRACR.FRM_APP);
        if (!CIRACR.FRM_APP.equalsIgnoreCase("TD")) {
            B031_INQ_NCD_INF_BY_AC();
            if (pgmRtn) return;
        } else {
            B032_INQ_NCD_INF_BY_AC();
            if (pgmRtn) return;
        }
    }
    public void B031_INQ_NCD_INF_BY_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CIRACR.KEY.AGR_NO;
        CIRACRL.KEY.AC_REL = "07";
        T000_STARTBR_CITACRL_BY_REL_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, CIRACRL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NCD INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NCD_INF_NOTFND);
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if ((CICSNCD.DATA.OPN_DT_B == 0 
                || CICSNCD.DATA.OPN_DT_B <= CIRACR.OPEN_DT) 
                && (CICSNCD.DATA.OPN_DT_E == 0 
                || CIRACR.OPEN_DT <= CICSNCD.DATA.OPN_DT_E) 
                && (CICSNCD.DATA.AC_STS == ' ' 
                || CICSNCD.DATA.AC_STS == CIRACR.STS)) {
                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                    && WS_CURRENT_ROW <= WS_MAX_ROW 
                    && WS_I <= WS_PAGE_ROW) {
                    R000_TRANS_DATA_TO_OUTPUT();
                    if (pgmRtn) return;
                }
                WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
            }
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B032_INQ_NCD_INF_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CICSNCD.DATA.AGR_NO;
        CIRACAC.PROD_CD = CICSNCD.DATA.PROD_CD;
        T000_STARTBR_CITACAC_BY_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NCD INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NCD_INF_NOTFND);
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICSNCD.DATA.OPN_DT_B == 0 
                || CICSNCD.DATA.OPN_DT_B <= CIRACAC.OPEN_DT) 
                && (CICSNCD.DATA.OPN_DT_E == 0 
                || CIRACAC.OPEN_DT <= CICSNCD.DATA.OPN_DT_E) 
                && (CICSNCD.DATA.AC_STS == ' ' 
                || CICSNCD.DATA.AC_STS == CIRACAC.ACAC_STS)) {
                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                    && WS_CURRENT_ROW <= WS_MAX_ROW 
                    && WS_I <= WS_PAGE_ROW) {
                    R000_TRANS_DATA_TO_OUTPUT();
                    if (pgmRtn) return;
                    WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                } else {
                    WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                }
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_INQ_NCD_INF_BY_CI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSNCD.DATA.CI_NO);
        if (CICSNCD.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSNCD.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CICSNCD.DATA.CI_NO;
        CIRACAC.PROD_CD = CICSNCD.DATA.PROD_CD;
        T000_STARTBR_CITACAC_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NCD INF NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NCD_INF_NOTFND);
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((CICSNCD.DATA.OPN_DT_B == 0 
                || CICSNCD.DATA.OPN_DT_B <= CIRACAC.OPEN_DT) 
                && (CICSNCD.DATA.OPN_DT_E == 0 
                || CIRACAC.OPEN_DT <= CICSNCD.DATA.OPN_DT_E) 
                && (CICSNCD.DATA.AC_STS == ' ' 
                || CICSNCD.DATA.AC_STS == CIRACAC.ACAC_STS)) {
                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                    && WS_CURRENT_ROW <= WS_MAX_ROW 
                    && WS_I <= WS_PAGE_ROW) {
                    R000_TRANS_DATA_TO_OUTPUT();
                    if (pgmRtn) return;
                    WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                } else {
                    WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                }
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CIRACAC.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = CIRACAC.AGR_NO;
        TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
        TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        CICFA06.FMT.DATA[WS_I-1].AGR_NO = CIRACAC.AGR_NO;
        CICFA06.FMT.DATA[WS_I-1].AC_SEQ = CIRACAC.AGR_SEQ;
        CICFA06.FMT.DATA[WS_I-1].ACAC_NO = CIRACAC.KEY.ACAC_NO;
        if (CIRBAS.CI_TYP != '1') {
            CICFA06.FMT.DATA[WS_I-1].AC_CNM = CIRACR.AC_CNM;
        } else {
            CICFA06.FMT.DATA[WS_I-1].AC_CNM = CIRBAS.CI_NM;
        }
        CICFA06.FMT.DATA[WS_I-1].CASH_AC_NO = TDCACE.DATA[1-1].OPEN_DR_AC;
        CICFA06.FMT.DATA[WS_I-1].CCY = TDCACE.DATA[1-1].CCY;
        CICFA06.FMT.DATA[WS_I-1].BAL = TDCACE.DATA[1-1].BAL;
        CICFA06.FMT.DATA[WS_I-1].BAL_ABLE = TDCACE.DATA[1-1].KY_BAL;
        CICFA06.FMT.DATA[WS_I-1].START_DT = TDCACE.DATA[1-1].SDT;
        CICFA06.FMT.DATA[WS_I-1].OPEN_AMT = TDCACE.DATA[1-1].PBAL;
        CICFA06.FMT.DATA[WS_I-1].EFF_DT = TDCACE.DATA[1-1].OPEN_DATE;
        CICFA06.FMT.DATA[WS_I-1].EXP_DT = TDCACE.DATA[1-1].DDT;
        CICFA06.FMT.DATA[WS_I-1].BV_TYP = TDCACE.DATA[1-1].BV_TYP;
        CICFA06.FMT.DATA[WS_I-1].BV_NO = TDCACE.DATA[1-1].BV_NO;
        CICFA06.FMT.DATA[WS_I-1].PROD_CD = TDCACE.DATA[1-1].PROD_CD;
        CICFA06.FMT.DATA[WS_I-1].PROD_TERM = TDCACE.DATA[1-1].TERM;
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = CIRACR.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CICFA06.FMT.DATA[WS_I-1].CDESC = BPCPQPRD.PRDT_NAME;
        CICFA06.FMT.DATA[WS_I-1].ACTI_NO = TDCACE.DATA[1-1].ACTI_NO;
        CICFA06.FMT.DATA[WS_I-1].NUM_RAT = TDCACE.DATA[1-1].INT_RAT;
        CICFA06.FMT.DATA[WS_I-1].ADV_RAT = TDCACE.DATA[1-1].PRV_RAT;
        CICFA06.FMT.DATA[WS_I-1].ARR_RAT = TDCACE.DATA[1-1].OVE_RAT;
        CICFA06.FMT.DATA[WS_I-1].EXC_LVL = TDCACE.PAGE_INF.CROS_DR_FLG;
        CICFA06.FMT.DATA[WS_I-1].DRAW_MTH = TDCACE.PAGE_INF.DRAW_MTH;
        CICFA06.FMT.DATA[WS_I-1].STS = CIRACAC.ACAC_STS;
        if (CIRACAC.ACAC_STS != '1') {
            CICFA06.FMT.DATA[WS_I-1].DEP_NUM = TDCACE.DATA[1-1].PART_NUM;
            CICFA06.FMT.DATA[WS_I-1].APPO_DATE = TDCACE.DATA[1-1].PART_DATE;
        } else {
            CICFA06.FMT.DATA[WS_I-1].DEP_NUM = (short) (TDCACE.DATA[1-1].PART_NUM + 1);
            CICFA06.FMT.DATA[WS_I-1].APPO_DATE = TDCACE.DATA[1-1].CLO_DATE;
        }
        CICFA06.FMT.DATA[WS_I-1].REAL_NUM = CICFA06.FMT.DATA[WS_I-1].OPEN_AMT - CICFA06.FMT.DATA[WS_I-1].BAL + TDCACE.DATA[1-1].DRW_INT;
        CICFA06.FMT.DATA[WS_I-1].PRNUM_NUM = TDCACE.DATA[1-1].HOLD_NUM;
        CICFA06.FMT.DATA[WS_I-1].OPEN_BR = TDCACE.DATA[1-1].OPEN_BR;
        CICFA06.FMT.DATA[WS_I-1].OPERATOR = TDCACE.DATA[1-1].OPEN_TLR;
        CICFA06.FMT.DATA[WS_I-1].CURR_NUM = TDCACE.DATA[1-1].ACCR_INT;
        CICFA06.FMT.DATA[WS_I-1].ACO_ATSW = TDCACE.DATA[1-1].ACO_STSW;
        WS_I = WS_I + 1;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CICFA06.FMT.CI_NO = CIRBAS.KEY.CI_NO;
        CICFA06.FMT.ID_NO = CIRBAS.ID_NO;
        CICFA06.FMT.ID_TYP = CIRBAS.ID_TYPE;
        WS_CURRENT_ROW = WS_CURRENT_ROW - 1;
        WS_I = WS_I - 1;
        CICFA06.FMT.TOTAL_NUM = WS_CURRENT_ROW;
        WS_RECORD_NUM = WS_CURRENT_ROW % WS_PAGE_ROW;
        CICFA06.FMT.TOTAL_PAGE = (int) ((WS_CURRENT_ROW - WS_RECORD_NUM) / WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            CICFA06.FMT.TOTAL_PAGE = CICFA06.FMT.TOTAL_PAGE + 1;
        }
        CICFA06.FMT.CURR_PAGE = CICSNCD.DATA.PAGE_NUM;
        CICFA06.FMT.PAGE_ROW = WS_I;
        if (CICFA06.FMT.CURR_PAGE >= CICFA06.FMT.TOTAL_PAGE 
            || CICFA06.FMT.TOTAL_PAGE == 0) {
            CICFA06.FMT.LAST_PAGE = 'Y';
        } else {
            CICFA06.FMT.LAST_PAGE = 'N';
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIA06";
        SCCFMT.DATA_PTR = CICFA06;
        SCCFMT.DATA_LEN = 4007;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
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
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACAC_DEFAULT() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO";
        CITACAC_RD.where = "SUBSTR ( ACAC_CTL , 2 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACAC, this, CITACAC_RD);
    }
    public void T000_STARTBR_CITACRL_BY_REL_AC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO , AC_REL";
        CITACRL_BR.rp.where = "REL_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void T000_STARTBR_CITACAC_BY_CI() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO , PROD_CD";
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC_BY_AC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO , PROD_CD";
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
