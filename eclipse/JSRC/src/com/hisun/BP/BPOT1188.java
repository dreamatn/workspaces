package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.VT.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1188 {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP078";
    String CPN_S_P_GET_FEE = "BP-S-P-GET-FEE";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String WS_TS = " ";
    short WS_I = 0;
    String WS_CHG_AC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPCSMUTF BPCSMUTF = new BPCSMUTF();
    BPCOCHGF BPCOCHGF = new BPCOCHGF();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPRFBAS BPRFBAS = new BPRFBAS();
    CIRACR CIRACR = new CIRACR();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DCCURHLD DCCURHLD = new DCCURHLD();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    BPB1188_AWA_1188 BPB1188_AWA_1188;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1188 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1188_AWA_1188>");
        BPB1188_AWA_1188 = (BPB1188_AWA_1188) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_CN();
        B020_MULT_FEE_S_CN();
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB1188_AWA_1188.FEE_CODE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_MUSTINPUT);
        }
        if (BPB1188_AWA_1188.FABF_AC.trim().length() > 0) {
            WS_CHG_AC = BPB1188_AWA_1188.FABF_AC;
        } else {
            WS_CHG_AC = BPB1188_AWA_1188.CHG_AC;
        }
    }
    public void B020_MULT_FEE_S_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        IBS.init(SCCGWA, DCCURHLD);
        if (BPB1188_AWA_1188.AC_TYP == '0') {
            DDCUDRAC.AC = WS_CHG_AC;
        } else {
            DDCUDRAC.CARD_NO = WS_CHG_AC;
            DDCUDRAC.BV_TYP = '1';
        }
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.CCY = BPB1188_AWA_1188.CCY;
        DDCUDRAC.TX_AMT = BPB1188_AWA_1188.FEE_AMT;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_MMO = BPB1188_AWA_1188.MMO;
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.TSTS_TABL = "0016";
        DDCUDRAC.FEE_FLG = 'Y';
        S000_CALL_DDZUDRAC();
        S000_WRITE_EVENT();
    }
    public void S000_WRITE_EVENT() throws IOException,SQLException,Exception {
        for (WS_I = 1; BPB1188_AWA_1188.FEE_INFO[WS_I-1].FEE_BR != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.PROD_CODE = BPB1188_AWA_1188.FEE_CODE;
            BPCPOEWA.DATA.PROD_CODE_REL = BPB1188_AWA_1188.PRD_CODE;
            BPCPOEWA.DATA.CNTR_TYPE = "FEES";
            BPCPOEWA.DATA.BR_OLD = BPB1188_AWA_1188.FEE_INFO[WS_I-1].FEE_BR;
            BPCPOEWA.DATA.BR_NEW = BPB1188_AWA_1188.FEE_INFO[WS_I-1].FEE_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPB1188_AWA_1188.CCY;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.THEIR_AC = WS_CHG_AC;
            if (WS_CHG_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = WS_CHG_AC;
                S000_CALL_CIZACCU();
                BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            }
            CEP.TRC(SCCGWA, "AWA-BR");
            CEP.TRC(SCCGWA, BPB1188_AWA_1188.FEE_INFO[WS_I-1].FEE_BR);
            if (BPB1188_AWA_1188.FEE_INFO[WS_I-1].Y_AMT > 0) {
                IBS.init(SCCGWA, VTCPQTAX);
                VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
                VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                VTCPQTAX.INPUT_DATA.BR = BPB1188_AWA_1188.FEE_INFO[WS_I-1].FEE_BR;
                VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
                VTCPQTAX.INPUT_DATA.PROD_CD = BPB1188_AWA_1188.FEE_CODE;
                VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPB1188_AWA_1188.PRD_CODE;
                CEP.TRC(SCCGWA, WS_CHG_AC);
                if (WS_CHG_AC.trim().length() > 0) {
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = WS_CHG_AC;
                    S000_CALL_CIZACCU();
                    VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
                }
                VTCPQTAX.INPUT_DATA.CCY = BPB1188_AWA_1188.CCY;
                VTCPQTAX.INPUT_DATA.SH_AMT = BPB1188_AWA_1188.FEE_INFO[WS_I-1].Y_AMT;
                VTCPQTAX.INPUT_DATA.INQ_TAX_FLG = 'W';
                S000_CALL_VTZPQTAX();
                BPCPOEWA.DATA.EVENT_CODE = "MA";
                BPCPOEWA.DATA.AMT_INFO[5-1].AMT = BPB1188_AWA_1188.FEE_INFO[WS_I-1].Y_AMT - VTCPQTAX.OUTPUT_DATA.TAX_AMT;
                S000_CALL_BPZPOEWA();
            }
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPOEWA.RC);
        }
    }
    public void S000_OUT_TS() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
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
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
