package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBCVOU {
    DBParm CITBAS_RD;
    int JIBS_tmp_int;
    brParm CITACR_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm CITACAC_RD;
    DBParm DDTVCH_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CIA04";
    int K_PAGE_MAX_ROW = 30;
    short WS_I01 = 0;
    short WS_I02 = 0;
    short WS_PAGE_ROW = 0;
    int WS_NOW_PAGE_NUM = 0;
    int WS_NOW_ROW_NUM = 0;
    int WS_SUM_PAGE = 0;
    int WS_NOW_PAGE_ROW_SUM = 0;
    char WS_ACR_FLG = ' ';
    char WS_ACAC_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    DDRVCH DDRVCH = new DDRVCH();
    CICFA04 CICFA04 = new CICFA04();
    DDCSCINM DDCSCINM = new DDCSCINM();
    TDCACE TDCACE = new TDCACE();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBCVOU CICBCVOU;
    public void MP(SCCGWA SCCGWA, CICBCVOU CICBCVOU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBCVOU = CICBCVOU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBCVOU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBCVOU.RC);
        IBS.init(SCCGWA, CICFA04);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_ACR_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICBCVOU.DATA.PAGE_ROW > K_PAGE_MAX_ROW) {
            WS_PAGE_ROW = (short) K_PAGE_MAX_ROW;
        } else {
            WS_PAGE_ROW = (short) CICBCVOU.DATA.PAGE_ROW;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICBCVOU.DATA.CI_NO;
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFA04.FMT.CI_NO = CICBCVOU.DATA.CI_NO;
            CICFA04.FMT.CURR_PAGE = CICBCVOU.DATA.PAGE_NUM;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_INPUT_ERR, CICBCVOU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICBCVOU.DATA.CI_NO;
        CIRACR.STS = '0';
        S000_STARTBR_CITACR_AGR();
        if (pgmRtn) return;
        S000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (WS_ACR_FLG == 'F') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICBCVOU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        while (WS_ACR_FLG != 'F') {
            if (CIRACR.FRM_APP.equalsIgnoreCase("TD")) {
                R000_TD_GET_BV_INFO();
                if (pgmRtn) return;
            } else if (CIRACR.FRM_APP.equalsIgnoreCase("DD")) {
                R000_DD_GET_BV_INFO();
                if (pgmRtn) return;
            } else if (CIRACR.FRM_APP.equalsIgnoreCase("DC")) {
                R000_DC_GET_BV_INFO();
                if (pgmRtn) return;
            } else {
            }
            S000_READNEXT_CITACR();
            if (pgmRtn) return;
            WS_ACAC_FLG = ' ';
        }
        S000_ENDBR_TABLE_CITACR();
        if (pgmRtn) return;
        WS_NOW_ROW_NUM = WS_I01 % WS_PAGE_ROW;
        WS_SUM_PAGE = (int) ((WS_I01 - WS_NOW_ROW_NUM) / WS_PAGE_ROW);
        if (WS_NOW_ROW_NUM != 0) {
            WS_SUM_PAGE = WS_SUM_PAGE + 1;
        }
        if (CICBCVOU.DATA.PAGE_NUM > WS_SUM_PAGE) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICBCVOU.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (CICBCVOU.DATA.PAGE_NUM == WS_SUM_PAGE) {
            CICFA04.FMT.LAST_PAGE = 'Y';
        } else if (CICBCVOU.DATA.PAGE_NUM < WS_SUM_PAGE) {
            CICFA04.FMT.LAST_PAGE = 'N';
        } else {
        }
        CICFA04.FMT.TOTAL_PAGE = WS_SUM_PAGE;
        CICFA04.FMT.TOTAL_NUM = WS_I01;
        CICFA04.FMT.PAGE_ROW = WS_NOW_PAGE_ROW_SUM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFA04;
        SCCFMT.DATA_LEN = 2617;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TD_GET_BV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
        CIRACAC.ACAC_STS = '0';
        S000_STARTBR_TABLE_CITACAC();
        if (pgmRtn) return;
        S000_READNEXT_TABLE_CITACAC();
        if (pgmRtn) return;
        while (WS_ACAC_FLG != 'N') {
            IBS.init(SCCGWA, TDCACE);
            TDCACE.PAGE_INF.AC_NO = CIRACR.KEY.AGR_NO;
            TDCACE.PAGE_INF.I_AC_SEQ = CIRACAC.AGR_SEQ;
            TDCACE.PAGE_INF.I_BV_NO = CIRACAC.BV_NO;
            TDCACE.PAGE_INF.QCD = "" + CIRACR.ENTY_TYP;
            JIBS_tmp_int = TDCACE.PAGE_INF.QCD.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) TDCACE.PAGE_INF.QCD = "0" + TDCACE.PAGE_INF.QCD;
            S000_CALL_TDZACE_GET();
            if (pgmRtn) return;
            for (WS_I02 = 1; (TDCACE.DATA[WS_I02-1].ACO_AC.trim().length() != 0 
                || TDCACE.DATA[WS_I02-1].BV_TYP != ' ' 
                || TDCACE.DATA[WS_I02-1].BV_NO.trim().length() != 0) 
                && WS_I02 <= 6; WS_I02 += 1) {
                R000_PUBLIC_PROC_COUNT();
                if (pgmRtn) return;
                if (CICBCVOU.DATA.PAGE_NUM == WS_NOW_PAGE_NUM) {
                    WS_NOW_PAGE_ROW_SUM = WS_NOW_PAGE_ROW_SUM + 1;
                    CICFA04.FMT.DATA[WS_I01-1].AGR_NO = TDCACE.PAGE_INF.AC_NO;
                    CICFA04.FMT.DATA[WS_I01-1].DRAW_MTH = TDCACE.PAGE_INF.DRAW_MTH;
                    CICFA04.FMT.DATA[WS_I01-1].ACAC_NO = TDCACE.DATA[WS_I02-1].ACO_AC;
                    CICFA04.FMT.DATA[WS_I01-1].BV_TYP = TDCACE.DATA[WS_I02-1].BV_TYP;
                    CICFA04.FMT.DATA[WS_I01-1].BV_NO = TDCACE.DATA[WS_I02-1].BV_NO;
                }
            }
            S000_READNEXT_TABLE_CITACAC();
            if (pgmRtn) return;
        }
        S000_ENDBR_TABLE_CITACAC();
        if (pgmRtn) return;
    }
    public void R000_DD_GET_BV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = CIRACR.KEY.AGR_NO;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM_GET();
        if (pgmRtn) return;
        if (DDCSCINM.OUTPUT_DATA.PSBK_NO.trim().length() > 0) {
            R000_PUBLIC_PROC_COUNT();
            if (pgmRtn) return;
            if (CICBCVOU.DATA.PAGE_NUM == WS_NOW_PAGE_NUM) {
                WS_NOW_PAGE_ROW_SUM = WS_NOW_PAGE_ROW_SUM + 1;
                IBS.init(SCCGWA, CIRACAC);
                IBS.init(SCCGWA, DDRVCH);
                CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
                DDRVCH.KEY.CUS_AC = CIRACR.KEY.AGR_NO;
                CIRACAC.ACAC_STS = '0';
                DDRVCH.VCH_TYPE = '1';
                T000_TABLE_CITACAC_GET_ACAC_NO();
                if (pgmRtn) return;
                T000_TABLE_DDTVCH_GET_PAY_TYPE();
                if (pgmRtn) return;
                CICFA04.FMT.DATA[WS_I01-1].AGR_NO = CIRACR.KEY.AGR_NO;
                CICFA04.FMT.DATA[WS_I01-1].BV_TYP = DDCSCINM.OUTPUT_DATA.BV_TYP;
                CICFA04.FMT.DATA[WS_I01-1].BV_NO = DDCSCINM.OUTPUT_DATA.PSBK_NO;
            }
        }
    }
    public void R000_DC_GET_BV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CIRACR.KEY.AGR_NO;
        S000_CALL_DCZUCINF_GET();
        if (pgmRtn) return;
        R000_PUBLIC_PROC_COUNT();
        if (pgmRtn) return;
        if (CICBCVOU.DATA.PAGE_NUM == WS_NOW_PAGE_NUM) {
            WS_NOW_PAGE_ROW_SUM = WS_NOW_PAGE_ROW_SUM + 1;
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = CIRACR.KEY.AGR_NO;
            CIRACAC.ACAC_STS = '0';
            T000_TABLE_CITACAC_GET_ACAC_NO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_MEDI);
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            CICFA04.FMT.DATA[WS_I01-1].AGR_NO = CIRACR.KEY.AGR_NO;
            CICFA04.FMT.DATA[WS_I01-1].BV_TYP = DCCUCINF.CARD_MEDI;
            CICFA04.FMT.DATA[WS_I01-1].BV_NO = DCCUCINF.CARD_NO;
        }
    }
    public void R000_PUBLIC_PROC_COUNT() throws IOException,SQLException,Exception {
        WS_I01 = (short) (WS_I01 + 1);
        WS_NOW_ROW_NUM = WS_I01 % WS_PAGE_ROW;
        WS_NOW_PAGE_NUM = (int) ((WS_I01 - WS_NOW_ROW_NUM) / WS_PAGE_ROW);
        if (WS_NOW_ROW_NUM != 0) {
            WS_NOW_PAGE_NUM = WS_NOW_PAGE_NUM + 1;
        } else {
            WS_NOW_ROW_NUM = K_PAGE_MAX_ROW;
        }
    }
    public void S000_STARTBR_CITACR_AGR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO,STS";
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
    }
    public void S000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'F';
        }
    }
    public void S000_ENDBR_TABLE_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void S000_STARTBR_TABLE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO,ACAC_STS";
        IBS.STARTBR(SCCGWA, CIRACAC, CITACAC_BR);
    }
    public void S000_READNEXT_TABLE_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACAC_FLG = 'N';
        }
    }
    public void S000_ENDBR_TABLE_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void S000_CALL_TDZACE_GET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_DDZSCINM_GET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_DCZUCINF_GET() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF, true);
        if (DCCUCINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICBCVOU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_TABLE_CITACAC_GET_ACAC_NO() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.eqWhere = "AGR_NO,ACAC_STS";
        CITACAC_RD.errhdl = true;
        CITACAC_RD.eqWhere = "DUPREC";
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFA04.FMT.DATA[WS_I01-1].ACAC_NO = CIRACAC.KEY.ACAC_NO;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1'
            || SCCGWA.COMM_AREA.DBIO_FLG == '4') {
        } else {
        }
    }
    public void T000_TABLE_DDTVCH_GET_PAY_TYPE() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFA04.FMT.DATA[WS_I01-1].DRAW_MTH = DDRVCH.PAY_TYPE;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
