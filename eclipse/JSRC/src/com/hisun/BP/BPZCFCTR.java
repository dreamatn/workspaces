package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.VT.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCFCTR {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFCTR_RD;
    DBParm BPTFAMO_RD;
    DBParm BPTFEHIS_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP233";
    double WS_ADJ_AMT = 0;
    double WS_VAT_AMT = 0;
    double WS_CHG_AMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPRFAMO BPRFAMO = new BPRFAMO();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    BPCO1233 BPCO1233 = new BPCO1233();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCFCTR BPCCFCTR;
    public void MP(SCCGWA SCCGWA, BPCCFCTR BPCCFCTR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCFCTR = BPCCFCTR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCFCTR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCCFCTR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.ADJ_TYP);
        if (BPCCFCTR.DATA.ADJ_TYP == '1') {
            B010_ADJ_AMO();
            if (pgmRtn) return;
        } else if (BPCCFCTR.DATA.ADJ_TYP == '2') {
            B020_RTN_ALL();
            if (pgmRtn) return;
        } else if (BPCCFCTR.DATA.ADJ_TYP == '3') {
            B030_ADJ_DT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCCFCTR.DATA.ADJ_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B090_OUTPUT_INFO_CN();
        if (pgmRtn) return;
    }
    public void B010_ADJ_AMO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCCFCTR.DATA.CTRT_NO;
        T000_READ_BPTFCTR_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "FCTR NOT FOUND");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFCTR.FEE_STATUS == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_COM, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFCTR.FEE_STATUS == '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_DEL, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCCFCTR.DATA.ADJ_AMT == 0) {
            CEP.TRC(SCCGWA, "ADJ AMT MUST INPUT");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.ADJ_AMT);
        WS_CHG_AMT = BPCCFCTR.DATA.ADJ_AMT;
        B011_ADJ_VTTAX();
        if (pgmRtn) return;
        B012_ADJ_BPTFCTR();
        if (pgmRtn) return;
        B013_ADJ_BPTFAMO();
        if (pgmRtn) return;
        R000_WRITE_DTFH_EVENT();
        if (pgmRtn) return;
        R000_CHARGE_AC();
        if (pgmRtn) return;
        R000_WRITE_FEHIS();
        if (pgmRtn) return;
    }
    public void B011_ADJ_VTTAX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFCTR.BOOK_CENTRE;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFCTR.FEE_TYPE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFCTR.PRD_TYPE;
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFCTR.CI_NO;
        VTCPQTAX.INPUT_DATA.CCY = BPRFCTR.CHARGE_CCY;
        VTCPQTAX.INPUT_DATA.SH_AMT = 0 - WS_CHG_AMT;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.SH_AMT);
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        WS_VAT_AMT = 0 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        WS_ADJ_AMT = WS_CHG_AMT - WS_VAT_AMT;
    }
    public void B012_ADJ_BPTFCTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFCTR.CHG_AMT_REAL);
        BPRFCTR.CHARGE_AMT = BPRFCTR.CHARGE_AMT - WS_CHG_AMT;
        BPRFCTR.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL - WS_CHG_AMT;
        if (BPRFCTR.FCHG_MIN_AMT > 0) {
            BPRFCTR.FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT - WS_VAT_AMT;
        }
        CEP.TRC(SCCGWA, BPRFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFCTR.CHG_AMT_REAL);
        CEP.TRC(SCCGWA, BPRFCTR.FCHG_MIN_AMT);
        T000_REWRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B013_ADJ_BPTFAMO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAMO);
        BPRFAMO.KEY.CTRT_NO = BPCCFCTR.DATA.CTRT_NO;
        T000_READ_BPTFAMO_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, BPRFAMO.FEE_AMO_AMT);
            CEP.TRC(SCCGWA, BPRFAMO.AMO_AMT_TOTAL);
            BPRFAMO.FEE_AMO_AMT = BPRFAMO.FEE_AMO_AMT - WS_ADJ_AMT;
            CEP.TRC(SCCGWA, BPRFAMO.FEE_AMO_AMT);
            if (BPRFAMO.FEE_AMO_AMT < BPRFAMO.AMO_AMT_TOTAL) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RTN_AMT_ERR, BPCCFCTR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_REWRITE_BPTFAMO();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_DTFH_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPRFCTR.FEE_TYPE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPRFCTR.PRD_TYPE;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = BPRFCTR.BOOK_CENTRE;
        BPCPOEWA.DATA.BR_NEW = BPRFCTR.BOOK_CENTRE;
        BPCPOEWA.DATA.CI_NO = BPRFCTR.CI_NO;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCCFCTR.DATA.RTN_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.EVENT_CODE = "DTFH";
        BPCPOEWA.DATA.REF_NO = BPCCFCTR.DATA.CTRT_NO;
        BPCPOEWA.DATA.AMT_INFO[9-1].AMT = WS_CHG_AMT;
        BPCPOEWA.DATA.AMT_INFO[7-1].AMT = WS_VAT_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[9-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void R000_CHARGE_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.RTN_TYP);
        if (BPCCFCTR.DATA.RTN_TYP == '0'
            || BPCCFCTR.DATA.RTN_TYP == '4'
            || BPCCFCTR.DATA.RTN_TYP == '5') {
            R000_CHARGE_DD_AC();
            if (pgmRtn) return;
        } else if (BPCCFCTR.DATA.RTN_TYP == '3') {
            R000_CHARGE_GL_AC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHARGE_DD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = BPCCFCTR.DATA.RTN_AC;
        DDCUDRAC.CCY = BPCCFCTR.DATA.RTN_CCY;
        DDCUDRAC.TX_AMT = 0 - WS_CHG_AMT;
        DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUDRAC.CCY_TYPE = BPCCFCTR.DATA.CCY_TYPE;
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void R000_CHARGE_GL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = BPCCFCTR.DATA.RTN_AC;
        AICUUPIA.DATA.CCY = BPCCFCTR.DATA.RTN_CCY;
        AICUUPIA.DATA.AMT = WS_CHG_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void R000_WRITE_FEHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFEHIS.KEY.JRN_SEQ = 1;
        BPRFEHIS.TX_CI = BPRFCTR.CI_NO;
        BPRFEHIS.REB_TYPE = 'N';
        BPRFEHIS.DRCRFLG = 'D';
        BPRFEHIS.FEE_CTRT_NO = BPRFCTR.KEY.CTRT_NO;
        BPRFEHIS.FEE_CODE = BPRFCTR.FEE_TYPE;
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        if (BPCCFCTR.DATA.RTN_TYP == '4' 
            || BPCCFCTR.DATA.RTN_TYP == '5') {
            BPRFEHIS.CARD_PSBK_NO = BPCCFCTR.DATA.RTN_AC;
        }
        BPRFEHIS.TX_CCY = BPCCFCTR.DATA.RTN_CCY;
        BPRFEHIS.CHG_BR = BPRFCTR.BOOK_CENTRE;
        BPRFEHIS.TX_AC = BPCCFCTR.DATA.RTN_AC;
        BPRFEHIS.CHG_AC_TY = BPCCFCTR.DATA.RTN_TYP;
        BPRFEHIS.FEE_CCY = BPCCFCTR.DATA.RTN_CCY;
        BPRFEHIS.FEE_BAS = 0 - WS_CHG_AMT;
        BPRFEHIS.FEE_AMT = BPRFEHIS.FEE_BAS;
        BPRFEHIS.CHG_CCY = BPCCFCTR.DATA.RTN_CCY;
        BPRFEHIS.CHG_BAS = BPRFEHIS.FEE_BAS;
        BPRFEHIS.CHG_AMT = BPRFEHIS.FEE_BAS;
        BPRFEHIS.ADJ_AMT = BPRFEHIS.FEE_BAS;
        BPRFEHIS.VAT_AMT = 0 - WS_VAT_AMT;
        BPRFEHIS.CHG_FLG = '0';
        BPRFEHIS.FEE_CTRT = BPCCFCTR.DATA.CTRT_NO;
        BPRFEHIS.CCY_TYPE = BPCCFCTR.DATA.CCY_TYPE;
        BPRFEHIS.TX_PROD = BPRFCTR.PRD_TYPE;
        BPRFEHIS.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_STS = 'N';
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_BPTFEHIS();
        if (pgmRtn) return;
    }
    public void B020_RTN_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCCFCTR.DATA.CTRT_NO;
        T000_READ_BPTFCTR_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "FCTR NOT FOUND");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFCTR.FEE_STATUS == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_COM, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFCTR.FEE_STATUS == '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_DEL, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B021_ADJ_BPTFAMO();
        if (pgmRtn) return;
        if (WS_ADJ_AMT != 0) {
            B022_ADJ_VTTAX();
            if (pgmRtn) return;
        } else {
            B011_ADJ_VTTAX();
            if (pgmRtn) return;
        }
        B023_ADJ_BPTFCTR();
        if (pgmRtn) return;
        R000_WRITE_DTFH_EVENT();
        if (pgmRtn) return;
        R000_CHARGE_AC();
        if (pgmRtn) return;
        R000_WRITE_FEHIS();
        if (pgmRtn) return;
    }
    public void B021_ADJ_BPTFAMO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAMO);
        BPRFAMO.KEY.CTRT_NO = BPCCFCTR.DATA.CTRT_NO;
        T000_READ_BPTFAMO_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, BPRFAMO.FEE_AMO_AMT);
            CEP.TRC(SCCGWA, BPRFAMO.AMO_AMT_TOTAL);
            WS_ADJ_AMT = BPRFAMO.FEE_AMO_AMT - BPRFAMO.AMO_AMT_TOTAL;
            BPRFAMO.FEE_AMO_AMT = BPRFAMO.AMO_AMT_TOTAL;
            T000_REWRITE_BPTFAMO();
            if (pgmRtn) return;
        } else {
            WS_CHG_AMT = BPRFCTR.CHARGE_AMT;
        }
        CEP.TRC(SCCGWA, WS_ADJ_AMT);
    }
    public void B022_ADJ_VTTAX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFCTR.BOOK_CENTRE;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFCTR.FEE_TYPE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPRFCTR.PRD_TYPE;
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFCTR.CI_NO;
        VTCPQTAX.INPUT_DATA.CCY = BPRFCTR.CHARGE_CCY;
        VTCPQTAX.INPUT_DATA.SHN_AMT = 0 - WS_ADJ_AMT;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.SHN_AMT);
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
        WS_VAT_AMT = 0 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        WS_CHG_AMT = WS_VAT_AMT + WS_ADJ_AMT;
    }
    public void B023_ADJ_BPTFCTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFCTR.CHG_AMT_REAL);
        BPRFCTR.CHARGE_AMT = BPRFCTR.CHARGE_AMT - WS_CHG_AMT;
        BPRFCTR.CHG_AMT_REAL = BPRFCTR.CHG_AMT_REAL - WS_CHG_AMT;
        if (BPRFCTR.FCHG_MIN_AMT > 0) {
            BPRFCTR.FCHG_MIN_AMT = BPRFCTR.FCHG_MIN_AMT - WS_VAT_AMT;
        }
        CEP.TRC(SCCGWA, BPRFCTR.CHARGE_AMT);
        CEP.TRC(SCCGWA, BPRFCTR.CHG_AMT_REAL);
        CEP.TRC(SCCGWA, BPRFCTR.FCHG_MIN_AMT);
        BPRFCTR.FEE_STATUS = '2';
        T000_REWRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B030_ADJ_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        BPRFCTR.KEY.CTRT_NO = BPCCFCTR.DATA.CTRT_NO;
        T000_READ_BPTFCTR_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "FCTR NOT FOUND");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFCTR.FEE_STATUS == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_COM, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRFCTR.FEE_STATUS == '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CTRT_ALREADY_DEL, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B031_ADJ_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B031_ADJ_BPTFCTR() throws IOException,SQLException,Exception {
        if (BPCCFCTR.DATA.ADJ_DT < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR, BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRFCTR.MATURITY_DATE);
        BPRFCTR.MATURITY_DATE = BPCCFCTR.DATA.ADJ_DT;
        CEP.TRC(SCCGWA, BPRFCTR.MATURITY_DATE);
        T000_REWRITE_BPTFCTR();
        if (pgmRtn) return;
    }
    public void B090_OUTPUT_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, BPCO1233);
        R000_MOVE_TO_BPCO1233();
        if (pgmRtn) return;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO1233;
        SCCFMT.DATA_LEN = 68;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MOVE_TO_BPCO1233() throws IOException,SQLException,Exception {
        BPCO1233.CTRT_NO = BPCCFCTR.DATA.CTRT_NO;
        BPCO1233.RTN_TYP = BPCCFCTR.DATA.ADJ_TYP;
        BPCO1233.RTN_AC = BPCCFCTR.DATA.RTN_AC;
        BPCO1233.RTN_CCY = BPCCFCTR.DATA.RTN_CCY;
        BPCO1233.CCY_TYP = BPCCFCTR.DATA.CCY_TYPE;
        BPCO1233.RTN_AMT = WS_CHG_AMT;
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.CTRT_NO);
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.ADJ_TYP);
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.RTN_AC);
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.RTN_CCY);
        CEP.TRC(SCCGWA, BPCCFCTR.DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, WS_CHG_AMT);
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        CEP.TRC(SCCGWA, BPCPOEWA.RC);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCCFCTR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void T000_READ_BPTFCTR_UPD() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.upd = true;
        IBS.READ(SCCGWA, BPRFCTR, BPTFCTR_RD);
    }
    public void T000_REWRITE_BPTFCTR() throws IOException,SQLException,Exception {
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        IBS.REWRITE(SCCGWA, BPRFCTR, BPTFCTR_RD);
    }
    public void T000_READ_BPTFAMO_UPD() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        BPTFAMO_RD.upd = true;
        IBS.READ(SCCGWA, BPRFAMO, BPTFAMO_RD);
    }
    public void T000_REWRITE_BPTFAMO() throws IOException,SQLException,Exception {
        BPTFAMO_RD = new DBParm();
        BPTFAMO_RD.TableName = "BPTFAMO";
        IBS.REWRITE(SCCGWA, BPRFAMO, BPTFAMO_RD);
    }
    public void T000_WRITE_BPTFEHIS() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.WRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
