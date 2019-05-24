package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMLRG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITFLMT_RD;
    DBParm CITFLRG_RD;
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    short WS_SEQ = 0;
    double WS_LMT_AMT = 0;
    double WS_CR_TOT_CAM = 0;
    double WS_DR_TOT_CAM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFX BPCFX = new BPCFX();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRFLMT CIRFLMT = new CIRFLMT();
    CIRFLRG CIRFLRG = new CIRFLRG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMLRG CICMLRG;
    public void MP(SCCGWA SCCGWA, CICMLRG CICMLRG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMLRG = CICMLRG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMLRG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMLRG.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CAL_FLRG_CAM();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLRG.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMLRG.DATA.TXN_TYP);
        CEP.TRC(SCCGWA, CICMLRG.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICMLRG.DATA.ACAC_NO);
        CEP.TRC(SCCGWA, CICMLRG.DATA.TXN_CCY);
        CEP.TRC(SCCGWA, CICMLRG.DATA.TXN_AMT);
        CEP.TRC(SCCGWA, CICMLRG.DATA.DC_FLG);
        if (CICMLRG.DATA.SVS_ADC_NO.trim().length() == 0 
            || CICMLRG.DATA.TXN_CCY.trim().length() == 0 
            || CICMLRG.DATA.TXN_TYP == ' ' 
            || CICMLRG.DATA.TXN_AMT == 0 
            || CICMLRG.DATA.DC_FLG == ' ' 
            || CICMLRG.DATA.CI_NO.trim().length() == 0 
            || CICMLRG.DATA.AGR_NO.trim().length() == 0 
            || CICMLRG.DATA.ACAC_NO.trim().length() == 0 
            || CICMLRG.DATA.AC_DATE == 0 
            || CICMLRG.DATA.JRN_NO == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICMLRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICMLRG.DATA.CI_NO;
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICMLRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICMLRG.DATA.AGR_NO;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMLRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRFLMT);
        CIRFLMT.KEY.SVS_ADC_NO = CICMLRG.DATA.SVS_ADC_NO;
        CEP.TRC(SCCGWA, CIRFLMT.KEY.SVS_ADC_NO);
        T000_READ_CITFLMT_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "FLMT INF NOT FND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICMLRG.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (CIRFLMT.SVS_ADC_STS == '1' 
                || CIRFLMT.SVS_ADC_STS == '2') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_STS_ABNORMAL, CICMLRG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CAL_FLRG_CAM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMLRG.DATA.TXN_CCY);
        CEP.TRC(SCCGWA, CIRFLMT.LTM_CCY);
        CEP.TRC(SCCGWA, CICMLRG.DATA.TXN_AMT);
        if (CIRFLMT.LTM_CCY.equalsIgnoreCase(CICMLRG.DATA.TXN_CCY)) {
            WS_LMT_AMT = CICMLRG.DATA.TXN_AMT;
        } else {
            IBS.init(SCCGWA, BPCFX);
            BPCFX.FUNC = '3';
            BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPCFX.EXR_TYPE = "MDR";
            BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCFX.BUY_CCY = CICMLRG.DATA.TXN_CCY;
            BPCFX.BUY_AMT = CICMLRG.DATA.TXN_AMT;
            BPCFX.SELL_CCY = CIRFLMT.LTM_CCY;
            S000_LINK_BPZSFX();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
            WS_LMT_AMT = BPCFX.SELL_AMT;
        }
        if (CICMLRG.DATA.TXN_TYP == '4' 
                && CICMLRG.DATA.DC_FLG == 'C' 
                && CIRFLMT.LMT_TYP == '2') {
            CIRFLMT.CR_TOT_CAM = CIRFLMT.CR_TOT_CAM + WS_LMT_AMT;
            if (CIRFLMT.CR_TOT_CAM > CIRFLMT.CR_TOT_LMT_AMT) {
                CEP.TRC(SCCGWA, "CR OVER FLMT");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CR_OVER_FLMT);
            }
        } else if (CICMLRG.DATA.TXN_TYP == '4' 
                && CICMLRG.DATA.DC_FLG == 'D' 
                && CIRFLMT.LMT_TYP == '3') {
            CIRFLMT.DR_TOT_CAM = CIRFLMT.DR_TOT_CAM + WS_LMT_AMT;
            if (CIRFLMT.DR_TOT_CAM > CIRFLMT.DR_TOT_LMT_AMT) {
                CEP.TRC(SCCGWA, "DR OVER FLMT");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DR_OVER_FLMT);
            }
        } else if (CICMLRG.DATA.TXN_TYP == '5' 
                && CICMLRG.DATA.DC_FLG == 'C' 
                && CIRFLMT.LMT_TYP == '2') {
            CIRFLMT.CR_TOT_CAM = CIRFLMT.CR_TOT_CAM - WS_LMT_AMT;
        } else if (CICMLRG.DATA.TXN_TYP == '5' 
                && CICMLRG.DATA.DC_FLG == 'D' 
                && CIRFLMT.LMT_TYP == '3') {
            CIRFLMT.DR_TOT_CAM = CIRFLMT.DR_TOT_CAM - WS_LMT_AMT;
        } else {
        }
        CIRFLMT.UPD_DT = CICMLRG.DATA.AC_DATE;
        CIRFLMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRFLMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_CITFLMT();
        if (pgmRtn) return;
        WS_CR_TOT_CAM = CIRFLMT.CR_TOT_CAM;
        WS_DR_TOT_CAM = CIRFLMT.DR_TOT_CAM;
        IBS.init(SCCGWA, CIRFLRG);
        CIRFLRG.KEY.JRN_NO = CICMLRG.DATA.JRN_NO;
        CIRFLRG.KEY.AC_DATE = CICMLRG.DATA.AC_DATE;
        T000_READ_CITFLRG_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SEQ = 1;
        } else {
            WS_SEQ = (short) (CIRFLRG.KEY.SEQ + 1);
        }
        R000_TRANS_CITFLRG_DATA();
        if (pgmRtn) return;
        T000_WRITE_CITFLRG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_CITFLRG_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRG);
        CIRFLRG.KEY.AC_DATE = CICMLRG.DATA.AC_DATE;
        CIRFLRG.KEY.JRN_NO = CICMLRG.DATA.JRN_NO;
        CIRFLRG.KEY.SEQ = WS_SEQ;
        CIRFLRG.CI_NO = CICMLRG.DATA.CI_NO;
        CIRFLRG.TXN_TYP = CICMLRG.DATA.TXN_TYP;
        CIRFLRG.AGR_NO = CICMLRG.DATA.AGR_NO;
        CIRFLRG.ACAC_NO = CICMLRG.DATA.ACAC_NO;
        CIRFLRG.SVS_ADC_NO = CICMLRG.DATA.SVS_ADC_NO;
        CIRFLRG.TXN_CCY = CICMLRG.DATA.TXN_CCY;
        if ((CICMLRG.DATA.DC_FLG == 'D' 
            && CICMLRG.DATA.TXN_TYP == '4') 
            || (CICMLRG.DATA.DC_FLG == 'C' 
            && CICMLRG.DATA.TXN_TYP == '5')) {
            CIRFLRG.TXN_AMT = CICMLRG.DATA.TXN_AMT;
        } else {
            CIRFLRG.TXN_AMT = 0 - CICMLRG.DATA.TXN_AMT;
        }
        CIRFLRG.LMT_TYP = CIRFLMT.LMT_TYP;
        CIRFLRG.LMT_KND = '1';
        CIRFLRG.LTM_CCY = CIRFLMT.LTM_CCY;
        CIRFLRG.CR_TOT_LMT_AMT = CIRFLMT.CR_TOT_LMT_AMT;
        CIRFLRG.DR_TOT_LMT_AMT = CIRFLMT.DR_TOT_LMT_AMT;
        CIRFLRG.CR_TOT_CAM = WS_CR_TOT_CAM;
        CIRFLRG.DR_TOT_CAM = WS_DR_TOT_CAM;
        CIRFLRG.BAL_LMT = CIRFLMT.BAL_LMT;
        CIRFLRG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRG.CRT_DT = CICMLRG.DATA.AC_DATE;
        CIRFLRG.UPD_DT = CICMLRG.DATA.AC_DATE;
        CIRFLRG.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRFLRG.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRFLRG";
        BPCPNHIS.INFO.FMT_ID_LEN = 536;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRFLRG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMLRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO "
            + "AND STS < > '1'";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
    }
    public void T000_READ_CITFLMT_UPD() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        CITFLMT_RD.upd = true;
        IBS.READ(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_REWRITE_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.REWRITE(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_READ_CITFLRG_FIRST() throws IOException,SQLException,Exception {
        CITFLRG_RD = new DBParm();
        CITFLRG_RD.TableName = "CITFLRG";
        CITFLRG_RD.eqWhere = "AC_DATE,JRN_NO";
        CITFLRG_RD.fst = true;
        CITFLRG_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, CIRFLRG, CITFLRG_RD);
    }
    public void T000_WRITE_CITFLRG() throws IOException,SQLException,Exception {
        CITFLRG_RD = new DBParm();
        CITFLRG_RD.TableName = "CITFLRG";
        IBS.WRITE(SCCGWA, CIRFLRG, CITFLRG_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
