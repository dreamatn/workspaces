package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.VT.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFEEC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFEHIS_RD;
    String K_PGM_NAME = "BPZSFEEC";
    String K_OUTPUT_FMT = "BP089";
    String K_TBL_FEHIS = "BPTFEHIS";
    String WS_TEMP_RECORD = " ";
    String WS_ERR_MSG = " ";
    double WS_ADJ_AMT = 0;
    double WS_VAT_AMT = 0;
    int WS_HIS_DATE = 0;
    char WS_TABLE_FLG = ' ';
    char WS_FEHIS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRTRT BPRTRT = new BPRTRT();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCOFEEC BPCOFEEC = new BPCOFEEC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPCPQCTR BPCPQCTR = new BPCPQCTR();
    AICUUPIA AICUUPIA = new AICUUPIA();
    BPCSFECT BPCSFECT = new BPCSFECT();
    SCCPJRN SCCPJRN = new SCCPJRN();
    SCRJRN SCRJRN = new SCRJRN();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    SCCTPCL SCCTPCL = new SCCTPCL();
    SCCGWA SCCGWA;
    SCCGMSG SCCGMSG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSFEEC BPCSFEEC;
    public void MP(SCCGWA SCCGWA, BPCSFEEC BPCSFEEC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFEEC = BPCSFEEC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSFEEC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_FEE_HIS_INFO();
        B025_GET_ORIGINAL_BR();
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_FLG);
        if (BPRFEHIS.CHG_FLG == '0') {
            B030_DISPOST_FEE_INCOME();
        } else if (BPRFEHIS.CHG_FLG == '1') {
            B040_DISPOSE_FEE_PAYOUT();
        } else {
        }
        B041_FEE_CTRT_REV();
        B045_EVEN_PROCESS();
        B050_UPDATE_FEE_HIS();
        B060_DATA_OUTPUT();
    }
    public void B010_GET_FEE_HIS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        CEP.TRC(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = BPCSFEEC.AC_DT;
        BPRFEHIS.KEY.JRNNO = BPCSFEEC.JRN_NO;
        BPRFEHIS.KEY.JRN_SEQ = BPCSFEEC.JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFEHIS);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        T000_QUERY_BPTFEHIS();
        CEP.TRC(SCCGWA, BPRFEHIS.TX_STS);
        if (BPRFEHIS.TX_STS == 'R' 
            || BPRFEHIS.TX_STS == 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ALREADY_CANCEL;
            S000_ERR_MSG_PROC();
        }
        if (BPRFEHIS.ADJ_AMT <= 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_NO_RETURN;
            S000_ERR_MSG_PROC();
        }
        if (BPRFEHIS.TX_CD.equalsIgnoreCase("9999156") 
            || BPRFEHIS.TX_CD.equalsIgnoreCase("9991262") 
            || BPRFEHIS.CHG_FLG == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USE_CANCEL_PLS;
            S000_ERR_MSG_PROC();
        }
        if (BPRFEHIS.TX_CD.equalsIgnoreCase("9991233") 
            || BPRFEHIS.TX_CD.equalsIgnoreCase("9991288") 
            || BPRFEHIS.TX_CD.equalsIgnoreCase("9999159") 
            || BPRFEHIS.TX_CD.equalsIgnoreCase("9999122") 
            || BPRFEHIS.TX_CD.equalsIgnoreCase("9991231") 
            || (BPRFEHIS.TX_CD.equalsIgnoreCase("9991187") 
            && BPRFEHIS.FEE_CTRT_NO.trim().length() > 0) 
            || (BPRFEHIS.TX_CD.equalsIgnoreCase("9997198") 
            && BPRFEHIS.FEE_CTRT_NO.trim().length() > 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_NOT_RETURN;
            S000_ERR_MSG_PROC();
        }
        if (BPRFEHIS.KEY.AC_DT != SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, SCCTPCL);
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
            SCCTPCL.SERV_AREA.SERV_CODE = "5001300001401";
            SCCTPCL.SERV_AREA.TRANS_FLG = '0';
            SCCTPCL.SERV_AREA.SERV_TYPE = 'Y';
            if (SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_SEQNO == null) SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_SEQNO = "";
            JIBS_tmp_int = SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_SEQNO.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_SEQNO += " ";
            JIBS_tmp_str[0] = "" + BPRFEHIS.KEY.JRNNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_SEQNO = JIBS_tmp_str[0] + SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_SEQNO.substring(12);
            SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_ORI_TRDT = BPRFEHIS.KEY.AC_DT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
            CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_OUT_CODE);
            if (SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_OUT_CODE.equalsIgnoreCase("001") 
                || SCCTPCL.INP_AREA.BD_BVMS_INQ.BVMS_OUT_CODE.equalsIgnoreCase("003")) {
                CEP.ERR(SCCGWA, "SC6038");
            }
        }
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        WS_HIS_DATE = BPRFEHIS.KEY.AC_DT;
        JIBS_tmp_str[0] = "" + WS_HIS_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        JIBS_tmp_str[1] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[1].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
        if (!JIBS_tmp_str[0].substring(0, 4).equalsIgnoreCase(JIBS_tmp_str[1].substring(0, 4))) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_IN_SAME_YEAR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_AC_TY);
        CEP.TRC(SCCGWA, BPRFEHIS.TX_TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (BPRFEHIS.CHG_AC_TY == '1' 
            && !BPRFEHIS.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
            S000_ERR_MSG_PROC();
        }
        WS_ADJ_AMT = 0 - BPRFEHIS.ADJ_AMT;
        CEP.TRC(SCCGWA, WS_ADJ_AMT);
    }
    public void B020_CHECK_CANCEL() throws IOException,SQLException,Exception {
        if (BPRFEHIS.TX_CD.equalsIgnoreCase("9991276")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COLLECTION_FEE_BACK;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRTRT);
        CEP.TRC(SCCGWA, BPRFEHIS.TX_CD);
        BPRTRT.KEY.CD = BPRFEHIS.TX_CD;
        CEP.TRC(SCCGWA, BPRTRT.KEY.CD);
        if (BPRTRT.KEY.CD == null) BPRTRT.KEY.CD = "";
        JIBS_tmp_int = BPRTRT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRTRT.KEY.CD += " ";
        BPRTRT.KEY.CD = BPRTRT.KEY.CD.substring(0, 7 - 1) + "9" + BPRTRT.KEY.CD.substring(7 + 1 - 1);
        CEP.TRC(SCCGWA, BPRTRT.KEY.CD);
        BPRTRT.KEY.TYP = "TRT";
        CEP.TRC(SCCGWA, BPRTRT.KEY.TYP);
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMR.DAT_PTR = BPRTRT;
        BPCPRMR.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPRTRT);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_USE_CANCEL_PLS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B025_GET_ORIGINAL_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPJRN);
        IBS.init(SCCGWA, SCRJRN);
        SCRJRN.AC_DATE = BPCSFEEC.AC_DT;
        SCRJRN.KEY.JRN_NO = BPCSFEEC.JRN_NO;
        SCCPJRN.FUNC = '1';
        CEP.TRC(SCCGWA, SCRJRN.AC_DATE);
        CEP.TRC(SCCGWA, SCRJRN.KEY.JRN_NO);
        CEP.TRC(SCCGWA, SCRJRN.CANCEL_IND);
        CEP.TRC(SCCGWA, SCRJRN.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
    }
    public void T000_QUERY_BPTFEHIS() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FEHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void B030_DISPOST_FEE_INCOME() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFEEC.FEE_CAL_TYP);
        if (BPCSFEEC.FEE_CAL_TYP == '0'
            || BPCSFEEC.FEE_CAL_TYP == '4'
            || BPCSFEEC.FEE_CAL_TYP == '5'
            || BPCSFEEC.FEE_CAL_TYP == '6'
            || BPCSFEEC.FEE_CAL_TYP == '7') {
            R000_DEBIT_DD_AC();
        } else if (BPCSFEEC.FEE_CAL_TYP == '1') {
            R000_CREDIT_CASH();
        } else if (BPCSFEEC.FEE_CAL_TYP == '2') {
            R000_DEBIT_IB_AC();
        } else if (BPCSFEEC.FEE_CAL_TYP == '3') {
            R000_DEBIT_GL_AC();
        } else {
        }
    }
    public void R000_DEBIT_DD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        if (BPCSFEEC.FEE_CAL_TYP == '0' 
            || BPCSFEEC.FEE_CAL_TYP == '6' 
            || BPCSFEEC.FEE_CAL_TYP == '7' 
            || BPCSFEEC.FEE_CAL_TYP == '5') {
            DDCUDRAC.AC = BPCSFEEC.FEE_CAL_AC;
        }
        if (BPCSFEEC.FEE_CAL_TYP == '4') {
            DDCUDRAC.CARD_NO = BPRFEHIS.CARD_PSBK_NO;
            CEP.TRC(SCCGWA, BPRFEHIS.CARD_PSBK_NO);
            CEP.TRC(SCCGWA, BPCSFEEC.FEE_CAL_AC);
        }
        if (BPCSFEEC.FEE_CAL_TYP == '6') {
            DDCUDRAC.CHQ_NO = BPRFEHIS.CARD_PSBK_NO;
            DDCUDRAC.CHQ_ISS_DATE = BPRFEHIS.SALE_DATE;
            DDCUDRAC.CHQ_TYPE = '2';
            DDCUDRAC.PCHQ_FLG = 'Y';
        }
        if (BPCSFEEC.FEE_CAL_TYP == '7') {
            DDCUDRAC.CHQ_NO = BPRFEHIS.CARD_PSBK_NO;
            DDCUDRAC.CHQ_ISS_DATE = BPRFEHIS.SALE_DATE;
            DDCUDRAC.CHQ_TYPE = '3';
            DDCUDRAC.PCHQ_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCUDRAC.CHQ_TYPE);
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.CCY = BPRFEHIS.CHG_CCY;
        DDCUDRAC.TX_AMT = WS_ADJ_AMT;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.REMARKS = "FEE CANCEL";
        DDCUDRAC.CCY_TYPE = BPRFEHIS.CCY_TYPE;
        DDCUDRAC.TX_TYPE = 'T';
        S000_CALL_DDZUDRAC();
    }
    public void R000_DEBIT_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.CCY = BPRFEHIS.CHG_CCY;
        BPCUABOX.AMT = BPRFEHIS.ADJ_AMT;
        S000_CALL_FEE_CHARGE_CASH();
    }
    public void R000_DEBIT_IB_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = BPCSFEEC.FEE_CAL_AC;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.AMT = WS_ADJ_AMT;
        IBCPOSTA.CCY = BPRFEHIS.CHG_CCY;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ENTRY_FLG = '1';
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        CEP.TRC(SCCGWA, IBCPOSTA.VAL_DATE);
        CEP.TRC(SCCGWA, IBCPOSTA.AMT);
        CEP.TRC(SCCGWA, IBCPOSTA.CCY);
        CEP.TRC(SCCGWA, IBCPOSTA.SIGN);
        CEP.TRC(SCCGWA, IBCPOSTA.ENTRY_FLG);
        S000_CALL_IBZDRAC();
    }
    public void R000_DEBIT_GL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = BPCSFEEC.FEE_CAL_AC;
        AICUUPIA.DATA.CCY = BPRFEHIS.CHG_CCY;
        AICUUPIA.DATA.AMT = WS_ADJ_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = BPRFEHIS.CREV_NO;
        AICUUPIA.DATA.POST_NARR = BPRFEHIS.TX_MMO;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
    }
    public void B040_DISPOSE_FEE_PAYOUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFEEC.FEE_CAL_TYP);
        if (BPCSFEEC.FEE_CAL_TYP == '0'
            || BPCSFEEC.FEE_CAL_TYP == '4'
            || BPCSFEEC.FEE_CAL_TYP == '5'
            || BPCSFEEC.FEE_CAL_TYP == '6'
            || BPCSFEEC.FEE_CAL_TYP == '7') {
            R000_CREDIT_DD_AC();
        } else if (BPCSFEEC.FEE_CAL_TYP == '1') {
            R000_DEBIT_CASH();
        } else if (BPCSFEEC.FEE_CAL_TYP == '2') {
            R000_CREDIT_IB_AC();
        } else if (BPCSFEEC.FEE_CAL_TYP == '3') {
            R000_CREDIT_GL_AC();
        } else {
        }
    }
    public void R000_CREDIT_DD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        if (BPCSFEEC.FEE_CAL_TYP == '0' 
            || BPCSFEEC.FEE_CAL_TYP == '5') {
            DDCUCRAC.AC = BPCSFEEC.FEE_CAL_AC;
        }
        if (BPCSFEEC.FEE_CAL_TYP == '4') {
            DDCUCRAC.CARD_NO = BPRFEHIS.CARD_PSBK_NO;
        }
        DDCUCRAC.CCY = BPRFEHIS.CHG_CCY;
        DDCUCRAC.TX_AMT = WS_ADJ_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.CCY_TYPE = BPRFEHIS.CCY_TYPE;
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        S000_CALL_DDZUDRAC();
    }
    public void R000_CREDIT_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.CCY = BPRFEHIS.CHG_CCY;
        BPCUSBOX.AMT = BPRFEHIS.ADJ_AMT;
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
        BPCUSBOX.CASH_NO = "225";
        S000_CALL_FEE_SUB_CASH();
    }
    public void R000_CREDIT_IB_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = BPCSFEEC.FEE_CAL_AC;
        IBCPOSTA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCPOSTA.AMT = WS_ADJ_AMT;
        IBCPOSTA.CCY = BPRFEHIS.CHG_CCY;
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.ENTRY_FLG = '1';
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        CEP.TRC(SCCGWA, IBCPOSTA.VAL_DATE);
        CEP.TRC(SCCGWA, IBCPOSTA.AMT);
        CEP.TRC(SCCGWA, IBCPOSTA.CCY);
        CEP.TRC(SCCGWA, IBCPOSTA.SIGN);
        CEP.TRC(SCCGWA, IBCPOSTA.ENTRY_FLG);
        S000_CALL_IBZCRAC();
    }
    public void R000_CREDIT_GL_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = BPCSFEEC.FEE_CAL_AC;
        AICUUPIA.DATA.CCY = BPRFEHIS.CHG_CCY;
        AICUUPIA.DATA.AMT = WS_ADJ_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = BPRFEHIS.CREV_NO;
        AICUUPIA.DATA.POST_NARR = BPRFEHIS.TX_MMO;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        S000_CALL_AIZUUPIA();
    }
    public void B041_FEE_CTRT_REV() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CTRT_NO);
        if (BPRFEHIS.FEE_CTRT_NO == null) BPRFEHIS.FEE_CTRT_NO = "";
        JIBS_tmp_int = BPRFEHIS.FEE_CTRT_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPRFEHIS.FEE_CTRT_NO += " ";
        if (BPRFEHIS.FEE_CTRT_NO.substring(0, 5).equalsIgnoreCase("73751")) {
            BPCSFECT.FUNC_CODE = 'R';
            BPCSFECT.CTNO = BPRFEHIS.FEE_CTRT_NO;
            BPCSFECT.CHG_FLG = 'N';
            S000_CALL_BPZSFECT();
        }
    }
    public void B045_EVEN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPRFEHIS.FEE_CODE;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = BPRFEHIS.CHG_BR;
        BPCPOEWA.DATA.BR_NEW = BPRFEHIS.CHG_BR;
        BPCPOEWA.DATA.CI_NO = BPRFEHIS.TX_CI;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPRFEHIS.CHG_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.DESC = BPRFEHIS.REMARK;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.DESC);
        if (BPRFEHIS.FEE_CTRT_NO.trim().length() > 0 
            && !BPRFEHIS.FEE_CTRT_NO.equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, BPCPQCTR);
            BPCPQCTR.KEY.CTRT_NO = BPRFEHIS.FEE_CTRT_NO;
            S000_CALL_BPZPQCTR();
        }
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CTRT_NO);
        if (BPRFEHIS.FEE_CTRT_NO.trim().length() > 0) {
            BPCPOEWA.DATA.REF_NO = BPRFEHIS.FEE_CTRT_NO;
            if (BPCPQCTR.INFO.BOOK_CENTRE != 0) {
                BPCPOEWA.DATA.BR_OLD = BPCPQCTR.INFO.BOOK_CENTRE;
                BPCPOEWA.DATA.BR_NEW = BPCPQCTR.INFO.BOOK_CENTRE;
            }
        }
        IBS.init(SCCGWA, VTCPQTAX);
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPRFEHIS.CHG_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPRFEHIS.FEE_CODE;
        VTCPQTAX.INPUT_DATA.CI_NO = BPRFEHIS.TX_CI;
        VTCPQTAX.INPUT_DATA.CCY = BPRFEHIS.FEE_CCY;
        VTCPQTAX.INPUT_DATA.SH_AMT = 0 - BPRFEHIS.ADJ_AMT;
        CEP.TRC(SCCGWA, VTCPQTAX.INPUT_DATA.SH_AMT);
        VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'W';
        S000_CALL_VTZPQTAX();
        WS_VAT_AMT = 0 - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        CEP.TRC(SCCGWA, BPRFEHIS.CHG_FLG);
        CEP.TRC(SCCGWA, WS_ADJ_AMT);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CTRT_NO);
        CEP.TRC(SCCGWA, WS_VAT_AMT);
        if (BPRFEHIS.TX_CD.equalsIgnoreCase("9999156")) {
            BPCPOEWA.DATA.EVENT_CODE = "FS";
            BPCPOEWA.DATA.AMT_INFO[6-1].AMT = BPRFEHIS.ADJ_AMT;
        } else if (BPRFEHIS.TX_CD.equalsIgnoreCase("9991262")) {
            BPCPOEWA.DATA.EVENT_CODE = "MA";
            BPCPOEWA.DATA.AMT_INFO[6-1].AMT = BPRFEHIS.ADJ_AMT;
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "ST";
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = BPRFEHIS.ADJ_AMT;
        }
        S000_CALL_BPZPOEWA();
    }
    public void B050_UPDATE_FEE_HIS() throws IOException,SQLException,Exception {
        T000_READ_BPTFEHIS_UPD_CN();
        BPRFEHIS.TX_STS = 'C';
        BPRFEHIS.TX_REV_DT = SCCGWA.COMM_AREA.TR_DATE;
        T000_REWRITE_BPTFEHIS_CN();
        BPRFEHIS.BAT_OTRT_DT = BPRFEHIS.KEY.AC_DT;
        BPRFEHIS.BAT_OTRT_JRN = BPRFEHIS.KEY.JRNNO;
        BPRFEHIS.BAT_OTRT_SEQ = BPRFEHIS.KEY.JRN_SEQ;
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFEHIS.KEY.JRN_SEQ = 1;
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.TX_STS = 'R';
        BPRFEHIS.TX_MMO = "Q102";
        BPRFEHIS.TX_REV_DT = 0;
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRFEHIS.SUP_TEL1 = SCCGMSG.SUP1_ID;
        BPRFEHIS.SUP_TEL2 = SCCGMSG.SUP2_ID;
        BPRFEHIS.FEE_BAS = 0 - BPRFEHIS.FEE_BAS;
        BPRFEHIS.FEE_FAV = 0 - BPRFEHIS.FEE_FAV;
        BPRFEHIS.FEE_AMT = 0 - BPRFEHIS.FEE_AMT;
        BPRFEHIS.CHG_BAS = 0 - BPRFEHIS.CHG_BAS;
        BPRFEHIS.CHG_FAV = 0 - BPRFEHIS.CHG_FAV;
        BPRFEHIS.CHG_AMT = 0 - BPRFEHIS.CHG_AMT;
        BPRFEHIS.ADJ_AMT = 0 - BPRFEHIS.ADJ_AMT;
        BPRFEHIS.DER_AMT = 0 - BPRFEHIS.DER_AMT;
        T000_WRITE_BPTFEHIS_CN();
    }
    public void T000_READ_BPTFEHIS_UPD_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FEHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FEHIS_FLG = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        IBS.REWRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
    }
    public void T000_WRITE_BPTFEHIS_CN() throws IOException,SQLException,Exception {
        BPTFEHIS_RD = new DBParm();
        BPTFEHIS_RD.TableName = "BPTFEHIS";
        BPTFEHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFEHIS, BPTFEHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FEHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFEEC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSFEEC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOFEEC);
        BPCOFEEC.TX_CODE = BPRFEHIS.TX_CD;
        BPCOFEEC.CHG_AC = BPRFEHIS.CHG_AC;
        BPCOFEEC.AC_DT = BPCSFEEC.AC_DT;
        BPCOFEEC.JRN_NO = BPCSFEEC.JRN_NO;
        BPCOFEEC.JRN_SEQ = BPCSFEEC.JRN_SEQ;
        BPCOFEEC.CHG_AC_TYP = BPRFEHIS.CHG_AC_TY;
        BPCOFEEC.FEE_CODE = BPRFEHIS.FEE_CODE;
        BPCOFEEC.CHG_AMT = BPRFEHIS.ADJ_AMT;
        BPCOFEEC.CHG_CCY = BPRFEHIS.CHG_CCY;
        BPCOFEEC.TX_TLR = BPRFEHIS.TX_TLR;
        BPCOFEEC.CHG_BR = BPRFEHIS.CHG_BR;
        BPCOFEEC.FEE_CAL_TYP = BPCSFEEC.FEE_CAL_TYP;
        BPCOFEEC.FEE_CAL_AC = BPCSFEEC.FEE_CAL_AC;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOFEEC;
        SCCFMT.DATA_LEN = 247;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_FEE_CHARGE_CASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
        if (BPCUABOX.RTNCODE != 0) {
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            WS_ERR_MSG = "BP" + WS_ERR_MSG.substring(2);
            if (WS_ERR_MSG == null) WS_ERR_MSG = "";
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_MSG += " ";
            JIBS_tmp_str[0] = "" + BPCUABOX.RTNCODE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_ERR_MSG = WS_ERR_MSG.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_ERR_MSG.substring(3 + JIBS_tmp_str[0].length() - 1);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_FEE_SUB_CASH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-DEBIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT, true);
    }
    public void S000_CALL_BPZPQCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-FEECTRSCH", BPCPQCTR);
        if (BPCPQCTR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQCTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZPJRN() throws IOException,SQLException,Exception {
        SCCPJRN.DATA_PTR = SCRJRN;
        SCCPJRN.DATA_LEN = 687;
        CEP.TRC(SCCGWA, "CALL SCZPJRN");
        IBS.CALLCPN(SCCGWA, "SC-JOURNAL-MAINTAIN", SCCPJRN);
        CEP.TRC(SCCGWA, SCCPJRN.RC);
        if (SCCPJRN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCPJRN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
