package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.IB.*;
import com.hisun.VT.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFEPAY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFEHIS_RD;
    double WS_SH_AMT = 0;
    double WS_VAT_AMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFEPAY BPCFEPAY;
    public void MP(SCCGWA SCCGWA, BPCFEPAY BPCFEPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFEPAY = BPCFEPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFEPAY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCFEPAY.RC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCFEPAY.INFO.VAT_AMT > 0) {
            B030_CHARGE_VAT();
        }
        if (BPCFEPAY.INFO.FEE_AMT > 0) {
            B040_CHARGE_AC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B020_WRITE_FZ_EVENT();
            B050_WRITE_FEE_HIS();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.VAT_AMT);
        if (BPCFEPAY.INFO.FEE_AMT < 0 
            || BPCFEPAY.INFO.VAT_AMT < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AMT_INPUT_ERR);
        }
    }
    public void B020_WRITE_FZ_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPCFEPAY.INFO.FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPCFEPAY.INFO.TX_PROD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCFEPAY.INFO.FEE_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPCPOEWA.DATA.DESC == null) BPCPOEWA.DATA.DESC = "";
        JIBS_tmp_int = BPCPOEWA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) BPCPOEWA.DATA.DESC += " ";
        if (BPCFEPAY.INFO.INV_NO == null) BPCFEPAY.INFO.INV_NO = "";
        JIBS_tmp_int = BPCFEPAY.INFO.INV_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCFEPAY.INFO.INV_NO += " ";
        BPCPOEWA.DATA.DESC = BPCFEPAY.INFO.INV_NO + BPCPOEWA.DATA.DESC.substring(8);
        if (BPCPOEWA.DATA.DESC == null) BPCPOEWA.DATA.DESC = "";
        JIBS_tmp_int = BPCPOEWA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) BPCPOEWA.DATA.DESC += " ";
        if (BPCFEPAY.INFO.INV_CD == null) BPCFEPAY.INFO.INV_CD = "";
        JIBS_tmp_int = BPCFEPAY.INFO.INV_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCFEPAY.INFO.INV_CD += " ";
        BPCPOEWA.DATA.DESC = BPCPOEWA.DATA.DESC.substring(0, 9 - 1) + BPCFEPAY.INFO.INV_CD + BPCPOEWA.DATA.DESC.substring(9 + 10 - 1);
        BPCPOEWA.DATA.EVENT_CODE = "FZ";
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.VAT_AMT);
        if (BPCFEPAY.INFO.FEE_AMT >= BPCFEPAY.INFO.VAT_AMT) {
            BPCPOEWA.DATA.AMT_INFO[21-1].AMT = BPCFEPAY.INFO.FEE_AMT - BPCFEPAY.INFO.VAT_AMT;
            CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[21-1].AMT);
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VAL_AMT_SHOULD_LESS);
        }
        BPCPOEWA.DATA.THEIR_AC = BPCFEPAY.INFO.AC_NO;
        S000_CALL_BPZPOEWA();
    }
    public void B021_WRITE_FZ_EVENT_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPCFEPAY.INFO.FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPCFEPAY.INFO.TX_PROD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCFEPAY.INFO.FEE_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (BPCPOEWA.DATA.DESC == null) BPCPOEWA.DATA.DESC = "";
        JIBS_tmp_int = BPCPOEWA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) BPCPOEWA.DATA.DESC += " ";
        if (BPCFEPAY.INFO.INV_NO == null) BPCFEPAY.INFO.INV_NO = "";
        JIBS_tmp_int = BPCFEPAY.INFO.INV_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) BPCFEPAY.INFO.INV_NO += " ";
        BPCPOEWA.DATA.DESC = BPCFEPAY.INFO.INV_NO + BPCPOEWA.DATA.DESC.substring(8);
        if (BPCPOEWA.DATA.DESC == null) BPCPOEWA.DATA.DESC = "";
        JIBS_tmp_int = BPCPOEWA.DATA.DESC.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) BPCPOEWA.DATA.DESC += " ";
        if (BPCFEPAY.INFO.INV_CD == null) BPCFEPAY.INFO.INV_CD = "";
        JIBS_tmp_int = BPCFEPAY.INFO.INV_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCFEPAY.INFO.INV_CD += " ";
        BPCPOEWA.DATA.DESC = BPCPOEWA.DATA.DESC.substring(0, 9 - 1) + BPCFEPAY.INFO.INV_CD + BPCPOEWA.DATA.DESC.substring(9 + 10 - 1);
        BPCPOEWA.DATA.EVENT_CODE = "FZ";
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.VAT_AMT);
        WS_SH_AMT = BPCFEPAY.INFO.FEE_AMT - BPCFEPAY.INFO.VAT_AMT;
        BPCPOEWA.DATA.AMT_INFO[22-1].AMT = WS_SH_AMT;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[22-1].AMT);
        BPCPOEWA.DATA.THEIR_AC = BPCFEPAY.INFO.AC_NO;
        S000_CALL_BPZPOEWA();
    }
    public void B030_CHARGE_VAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPCFEPAY.INFO.FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCFEPAY.INFO.TX_PROD;
        VTCPQTAX.INPUT_DATA.CCY = BPCFEPAY.INFO.FEE_CCY;
        VTCPQTAX.INPUT_DATA.SH_AMT = BPCFEPAY.INFO.FEE_AMT;
        VTCPQTAX.INPUT_DATA.POST_AMT = BPCFEPAY.INFO.VAT_AMT;
        VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'J';
        S000_CALL_VTZPQTAX();
    }
    public void B031_CHARGE_VAT_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPCFEPAY.INFO.FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPCFEPAY.INFO.TX_PROD;
        VTCPQTAX.INPUT_DATA.CCY = BPCFEPAY.INFO.FEE_CCY;
        VTCPQTAX.INPUT_DATA.SH_AMT = BPCFEPAY.INFO.FEE_AMT;
        WS_VAT_AMT = 0 - BPCFEPAY.INFO.VAT_AMT;
        VTCPQTAX.INPUT_DATA.POST_AMT = WS_VAT_AMT;
        VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'J';
        S000_CALL_VTZPQTAX();
    }
    public void B040_CHARGE_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFEPAY.INFO.AC_TYP);
        if (BPCFEPAY.INFO.AC_TYP == 'I') {
            IBS.init(SCCGWA, AICUUPIA);
            AICUUPIA.DATA.AC_NO = BPCFEPAY.INFO.AC_NO;
            AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.AMT = BPCFEPAY.INFO.FEE_AMT;
            AICUUPIA.DATA.CCY = BPCFEPAY.INFO.FEE_CCY;
            AICUUPIA.DATA.EVENT_CODE = "CR";
            AICUUPIA.DATA.RVS_NO = BPCFEPAY.INFO.CREV_NO;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.DESC);
            S000_CALL_AIZUUPIA();
        } else if (BPCFEPAY.INFO.AC_TYP == 'N') {
            IBS.init(SCCGWA, IBCPOSTA);
            IBCPOSTA.AC = BPCFEPAY.INFO.AC_NO;
            IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBCPOSTA.AMT = BPCFEPAY.INFO.FEE_AMT;
            IBCPOSTA.CCY = BPCFEPAY.INFO.FEE_CCY;
            IBCPOSTA.SIGN = 'C';
            IBCPOSTA.ENTRY_FLG = '1';
            S000_CALL_IBZCRAC();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTRIGHT);
        }
    }
    public void B050_WRITE_FEE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFEHIS.KEY.JRN_SEQ = 1;
        BPRFEHIS.CHG_AC = BPCFEPAY.INFO.AC_NO;
        if (BPCFEPAY.INFO.AC_TYP == 'I') {
            BPRFEHIS.CHG_AC_TY = '3';
        } else {
            BPRFEHIS.CHG_AC_TY = '2';
        }
        BPRFEHIS.CHG_FLG = '1';
        BPRFEHIS.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRFEHIS.CHG_CCY = BPCFEPAY.INFO.FEE_CCY;
        BPRFEHIS.ADJ_AMT = BPCFEPAY.INFO.FEE_AMT;
        BPRFEHIS.VAT_AMT = BPCFEPAY.INFO.VAT_AMT;
        BPRFEHIS.DRCRFLG = 'D';
        BPRFEHIS.FEE_CODE = BPCFEPAY.INFO.FEE_CODE;
        BPRFEHIS.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.TX_PROD = BPCFEPAY.INFO.TX_PROD;
        BPRFEHIS.TX_STS = 'N';
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_BPTFEHIS();
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_WRITE_BPTFEHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS);
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.WRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
