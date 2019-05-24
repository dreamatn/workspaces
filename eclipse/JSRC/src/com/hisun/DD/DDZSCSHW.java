package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCSHW {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    String K_OUTPUT_FMT = "DDP11";
    String CPN_UNIT_DRAW_PROC = "DD-UNIT-DRAW-PROC ";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String CPN_U_ADD_CBOX = "BP-U-ADD-CBOX      ";
    String CPN_U_SUB_CBOX = "BP-U-SUB-CBOX      ";
    String CPN_AMT_TBL_AUTH = "BP-F-AMT-TBL-AUTH  ";
    String CPN_BP_EX = "BP-EX           ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    String WS_BUY_CCY = " ";
    double WS_BUY_AMT = 0;
    String WS_SELL_CCY = " ";
    double WS_SELL_AMT = 0;
    int WS_TMP_DT = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICACCU CICACCU = new CICACCU();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCOCSHW DDCOCSHW = new DDCOCSHW();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCFX BPCFX = new BPCFX();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSCSHW DDCSCSHW;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCSHW DDCSCSHW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCSHW = DDCSCSHW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSCSHW return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCSHW.BV_TYP);
        CEP.TRC(SCCGWA, DDCSCSHW.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCSHW.AC);
        CEP.TRC(SCCGWA, DDCSCSHW.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCSHW.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSCSHW.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSCSHW.CHQ_ISS_DATE);
        CEP.TRC(SCCGWA, DDCSCSHW.CCY);
        CEP.TRC(SCCGWA, DDCSCSHW.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCSHW.CASH_AMT);
        CEP.TRC(SCCGWA, DDCSCSHW.TX_RMK);
        CEP.TRC(SCCGWA, DDCSCSHW.REMARKS);
        CEP.TRC(SCCGWA, DDCSCSHW.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSCSHW.PSWD);
        CEP.TRC(SCCGWA, DDCSCSHW.CHQ_PSWD);
        CEP.TRC(SCCGWA, DDCSCSHW.TX_MMO);
        B005_CHECK_INPUT_DATA();
        if (DDCSCSHW.BV_TYP == '6') {
            B015_WITHDRAW_FROM_AI_PROC();
        } else {
            B015_WITHDRAW_FROM_AC_PROC();
        }
        B020_CASH_PROC();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B030_AGENT_INF_PORC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_OUTPUT_PROC();
        }
    }
    public void B005_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSCSHW.AC.trim().length() == 0 
            && DDCSCSHW.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCSHW.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DDCSCSHW.CCY;
            S000_CALL_BPZQCCY();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSCSHW.CASH_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            if (DDCSCSHW.CASH_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_EXG_AMT_PROC() throws IOException,SQLException,Exception {
        if (!DDCSCSHW.CCY.equalsIgnoreCase(DDCSCSHW.CASH_CCY)) {
            WS_SELL_CCY = DDCSCSHW.CASH_CCY;
            WS_SELL_AMT = DDCSCSHW.CASH_AMT;
            WS_BUY_CCY = DDCSCSHW.CCY;
            R000_AMT_EX_PROC();
        } else {
            WS_BUY_AMT = DDCSCSHW.CASH_AMT;
        }
    }
    public void B012_CARD_LIMT_BAL_CHK() throws IOException,SQLException,Exception {
        if (DDCSCSHW.BV_TYP == '1') {
            IBS.init(SCCGWA, DCCPFTCK);
            if (DDCSCSHW.CARD_NO.trim().length() > 0) {
                DCCPFTCK.VAL.CARD_NO = DDCSCSHW.CARD_NO;
            } else {
                DCCPFTCK.VAL.CARD_NO = DDCSCSHW.AC;
            }
            CEP.TRC(SCCGWA, DCCPFTCK.VAL.CARD_NO);
            DCCPFTCK.VAL.REGN_TYP = '0';
            DCCPFTCK.VAL.TXN_TYPE = "01";
            DCCPFTCK.VAL.TXN_CCY = DDCSCSHW.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCSCSHW.CASH_AMT;
            DCCPFTCK.TRK2_DAT = DDCSCSHW.TRK_DATE2;
            DCCPFTCK.TRK3_DAT = DDCSCSHW.TRK_DATE3;
            S000_CALL_DCZPFTCK();
        }
    }
    public void B015_WITHDRAW_FROM_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDCSCSHW.AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = DDCSCSHW.CASH_AMT;
        AICUUPIA.DATA.CCY = DDCSCSHW.CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = DDCSCSHW.CREV_NO;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.DESC = DDCSCSHW.TX_RMK;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.DESC);
        S000_CALL_AIZUUPIA();
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_WITHDRAW_FROM_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'C';
        DDCUDRAC.AC = DDCSCSHW.AC;
        DDCUDRAC.PSBK_NO = DDCSCSHW.PSBK_NO;
        DDCUDRAC.CARD_NO = DDCSCSHW.CARD_NO;
        DDCUDRAC.CHQ_TYPE = DDCSCSHW.CHQ_TYPE;
        if (DDCSCSHW.CHQ_NO.trim().length() > 0) {
            DDCUDRAC.CHQ_NO = DDCSCSHW.CHQ_NO;
            DDCUDRAC.CHK_PSW_FLG = 'N';
        } else {
            DDCUDRAC.CHK_PSW_FLG = 'Y';
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("111610")) {
            DDCUDRAC.CHK_PSW_FLG = 'N';
        }
        DDCUDRAC.CHQ_ISS_DATE = DDCSCSHW.CHQ_ISS_DATE;
        DDCUDRAC.PAY_PSWD = DDCSCSHW.CHQ_PSWD;
        DDCUDRAC.TX_AMT = DDCSCSHW.CASH_AMT;
        DDCUDRAC.CCY = DDCSCSHW.CCY;
        DDCUDRAC.CCY_TYPE = DDCSCSHW.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCSCSHW.VAL_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, "GWA-AC-DATE");
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CEP.TRC(SCCGWA, "GWA-CAN-AC-DATE");
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.VAL_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUDRAC.TX_MMO = "G004";
        } else {
            DDCUDRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        }
        DDCUDRAC.NARRATIVE = DDCSCSHW.REMARKS;
        DDCUDRAC.REMARKS = DDCSCSHW.TX_RMK;
        DDCUDRAC.BV_TYP = DDCSCSHW.BV_TYP;
        DDCUDRAC.CHK_PSW = DDCSCSHW.CHK_PSW;
        DDCUDRAC.TRK_DATE2 = DDCSCSHW.TRK_DATE2;
        DDCUDRAC.TRK_DATE3 = DDCSCSHW.TRK_DATE3;
        DDCUDRAC.PSWD = DDCSCSHW.PSWD;
        DDCUDRAC.PSBK_SEQ = DDCSCSHW.PSBK_SEQ;
        CEP.TRC(SCCGWA, DDCUDRAC.HLD_NO);
        S000_CALL_DDZUDRAC();
        DDCSCSHW.TX_MMO = DDCUDRAC.TX_MMO;
    }
    public void B020_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUSBOX);
        BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUSBOX.CCY = DDCSCSHW.CCY;
        BPCUSBOX.AMT = DDCSCSHW.CASH_AMT;
        CEP.TRC(SCCGWA, BPCUSBOX.CCY);
        CEP.TRC(SCCGWA, BPCUSBOX.AMT);
        BPCUSBOX.OPP_AC = DDCSCSHW.AC;
        BPCUSBOX.CASH_NO = DDCSCSHW.CASH_NO;
        BPCUSBOX.APP_NO = DDCSCSHW.REG_CD;
        S000_CALL_BPZUSBOX();
    }
    public void B030_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        if (DDCSCSHW.AC.trim().length() > 0) {
            CICSAGEN.OUT_AC = DDCSCSHW.AC;
        } else {
            CICSAGEN.OUT_AC = DDCSCSHW.CARD_NO;
        }
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BUY_CCY);
        CEP.TRC(SCCGWA, WS_BUY_AMT);
        CEP.TRC(SCCGWA, WS_SELL_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = "TRE";
        BPCFX.BUY_CCY = WS_BUY_CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = DDCSCSHW.CASH_CCY;
        if (DDCSCSHW.EX_RATE != 0) {
            BPCFX.TRN_RATE = DDCSCSHW.EX_RATE;
        }
        S000_LINK_BPZSFX();
        WS_SELL_AMT = BPCFX.SELL_AMT;
        CEP.TRC(SCCGWA, WS_SELL_AMT);
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCSHW);
        DDCOCSHW.BV_TYP = ' ';
        DDCOCSHW.CARD_NO = " ";
        DDCOCSHW.PSBK_NO = " ";
        DDCOCSHW.CHQ_TYPE = ' ';
        DDCOCSHW.CHQ_NO = " ";
        DDCOCSHW.CHQ_ISS_DATE = 0;
        DDCOCSHW.BV_TYP = DDCSCSHW.BV_TYP;
        DDCOCSHW.CARD_NO = DDCSCSHW.CARD_NO;
        DDCOCSHW.PSBK_NO = DDCSCSHW.PSBK_NO;
        DDCOCSHW.AC = DDCSCSHW.AC;
        DDCOCSHW.ENG_NM = DDCUDRAC.ENG_NAME;
        DDCOCSHW.CHI_NM = DDCUDRAC.CHI_NAME;
        DDCOCSHW.CHQ_TYPE = DDCSCSHW.CHQ_TYPE;
        DDCOCSHW.CHQ_NO = DDCSCSHW.CHQ_NO;
        DDCOCSHW.CHQ_ISS_DATE = DDCSCSHW.CHQ_ISS_DATE;
        DDCOCSHW.CR_CCY = DDCSCSHW.CCY;
        DDCOCSHW.CCY_TYPE = DDCSCSHW.CCY_TYPE;
        DDCOCSHW.CR_CAMT = DDCSCSHW.CASH_AMT;
        DDCOCSHW.PAY_TYPE = DDCSCSHW.PAY_TYPE;
        DDCOCSHW.TX_REF = DDCSCSHW.TX_RMK;
        DDCOCSHW.REMARKS = DDCSCSHW.REMARKS;
        DDCOCSHW.TX_MMO = DDCSCSHW.TX_MMO;
        CEP.TRC(SCCGWA, DDCOCSHW.TX_MMO);
        CEP.TRC(SCCGWA, DDCOCSHW.ENG_NM);
        CEP.TRC(SCCGWA, DDCOCSHW.CHI_NM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCSHW;
        SCCFMT.DATA_LEN = 884;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_TX_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFAMTA);
        BPCFAMTA.FUNC = ' ';
        BPCFAMTA.AP_MMO = "DD";
        BPCFAMTA.TBL_NO = "0002";
        BPCFAMTA.CHNL = "CHNL";
        BPCFAMTA.CCY = DDCSCSHW.CCY;
        BPCFAMTA.AMT = DDCSCSHW.CASH_AMT;
        S000_CALL_BPZFAMTA();
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_BPZFAMTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_AMT_TBL_AUTH, BPCFAMTA);
        if (BPCFAMTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFAMTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNIT_DRAW_PROC, DDCUDRAC);
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
    }
    public void S000_LINK_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_ADD_CBOX, BPCUABOX);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_SUB_CBOX, BPCUSBOX);
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
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
