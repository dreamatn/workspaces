package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.DD.*;
import com.hisun.VT.WFCCAPV;
import com.hisun.WF.*;
import com.hisun.AI.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FXZSDRFX {
    DBParm FXTSUINF_RD;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm DDTHLD_BR = new brParm();
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_EXRPLMT = "EXRPLMT ";
    String K_PROD_CD_01 = "EX000001";
    String K_PROD_CD_02 = "EX000002";
    String K_PROD_CD_03 = "EX000003";
    String K_PROD_CD_04 = "EX000004";
    int K_COUNT_SQL = 100;
    int WK_MONTHS = 3;
    char WS_CHECK_RATE_FLG = ' ';
    String WS_ERR_MSG = " ";
    int WS_AC_SEQ1 = 0;
    int WS_AC_SEQ2 = 0;
    String WS_TR_FTZ_CD = " ";
    short WS_FLD_NO = 0;
    double WS_TOTAL_AMT = 0;
    char WS_DRCR_FLAG = ' ';
    short WS_CNT = 0;
    int WS_TEMP_DT = 0;
    String WS_MSGID = " ";
    String WS_SELL_CCY_CNTYCD = " ";
    String WS_BUY_CCY_CNTYCD = " ";
    char WS_END_FLG = ' ';
    double WS_HLD_AMT = 0;
    double WS_BAL = 0;
    char WS_RHLD_FLG = ' ';
    FXZSDRFX_WS_Q_OUTPUT_DATA WS_Q_OUTPUT_DATA = new FXZSDRFX_WS_Q_OUTPUT_DATA();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXCRDRFX FXCRDRFX = new FXCRDRFX();
    FXCODRFX FXCODRFX = new FXCODRFX();
    FXCPGNFX FXCPGNFX = new FXCPGNFX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    WFCCAPV WFCCAPV = new WFCCAPV();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    String WS_DD_AC1 = " ";
    String WS_BUY_CCY = " ";
    char WS_CCY_TYP = ' ';
    DDRHLD DDRHLD = new DDRHLD();
    FXRSUINF FXRSUINF = new FXRSUINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    BPRTLT BPRTLT;
    SCCAWAC SCCAWAC;
    FXCSDRFX FXCSDRFX;
    public void MP(SCCGWA SCCGWA, FXCSDRFX FXCSDRFX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCSDRFX = FXCSDRFX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZSDRFX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        CEP.TRC(SCCGWA, FXCSDRFX.DD_AC1);
        CEP.TRC(SCCGWA, FXCSDRFX.DD_AC2);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (FXCSDRFX.FUNC == 'B') {
            B010_BRW_REC_PROC();
            if (pgmRtn) return;
        } else if (FXCSDRFX.FUNC == 'I') {
            B040_QUERY_REC_PROC();
            if (pgmRtn) return;
        } else if (FXCSDRFX.FUNC == 'A') {
            CEP.TRC(SCCGWA, FXCSDRFX.TRA_AC);
            B050_ADD_REC_PROC();
            if (pgmRtn) return;
        } else if (FXCSDRFX.FUNC == 'R') {
            B060_REV_REC_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_IPT_FUNC_ERR);
        }
    }
    public void B010_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, FXCODRFX);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 908;
        SCCMPAG.SCR_ROW_CNT = 10;
        SCCMPAG.SCR_COL_CNT = 9;
        CEP.TRC(SCCGWA, SCCMPAG.MAX_COL_NO);
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, FXCRDRFX);
        IBS.init(SCCGWA, FXRDIRFX);
        FXCRDRFX.FUNC = 'B';
        FXCRDRFX.REC_PTR = FXRDIRFX;
        FXCRDRFX.REC_LEN = 2776;
        B020_COPY_DATA();
        if (pgmRtn) return;
        FXCRDRFX.OPT = 'S';
        S000_CALL_FXZRDRFX();
        if (pgmRtn) return;
        FXCRDRFX.FUNC = 'B';
        FXCRDRFX.OPT = 'R';
        S000_CALL_FXZRDRFX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXCRDRFX.RC.RC_CODE);
        CEP.TRC(SCCGWA, FXCRDRFX.RETURN_INFO);
        while (FXCRDRFX.RETURN_INFO != 'E') {
            CEP.TRC(SCCGWA, "DEVHZ491");
            B040_COMPAIR_BR();
            if (pgmRtn) return;
            if (BPCPRGST.LVL_RELATION == 'A') {
                CEP.TRC(SCCGWA, "DEVHZ405");
                B030_OUTPUT_DATA_PROCESS_LST();
                if (pgmRtn) return;
            }
            FXCRDRFX.FUNC = 'B';
            FXCRDRFX.OPT = 'R';
            S000_CALL_FXZRDRFX();
            if (pgmRtn) return;
        }
        FXCRDRFX.FUNC = 'B';
        FXCRDRFX.OPT = 'E';
        S000_CALL_FXZRDRFX();
        if (pgmRtn) return;
    }
    public void B040_COMPAIR_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = FXRDIRFX.KEY.UPD_BR;
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == FXRDIRFX.KEY.UPD_BR) {
            BPCPRGST.FLAG = 'Y';
            BPCPRGST.LVL_RELATION = 'A';
        } else {
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DEVHZ458");
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
    }
    public void B020_COPY_DATA() throws IOException,SQLException,Exception {
        FXRDIRFX.ID_TYP = FXCSDRFX.ID_TYP;
        FXRDIRFX.ID_NO = FXCSDRFX.ID_NO;
        FXRDIRFX.KEY.UPD_BR = FXCSDRFX.UPD_BR;
        FXRDIRFX.PROD_CD = FXCSDRFX.PROD_CD;
        FXRDIRFX.ES_REG_CD = FXCSDRFX.REG_CD;
        FXRDIRFX.CI_NO = FXCSDRFX.CI_NO;
        FXRDIRFX.TRA_AC = FXCSDRFX.TRA_AC;
        FXRDIRFX.CI_CNM = FXCSDRFX.CI_CNM;
        FXRDIRFX.CI_ENM = FXCSDRFX.CI_ENM;
        FXRDIRFX.BUY_CCY = FXCSDRFX.BUY_CCY;
        FXRDIRFX.BUY_AMT = FXCSDRFX.BUY_AMT;
        FXRDIRFX.SELL_CCY = FXCSDRFX.SELL_CCY;
        FXRDIRFX.SELL_AMT = FXCSDRFX.SELL_AMT;
        FXRDIRFX.VALUE_DT = FXCSDRFX.VALUE_DT;
        FXRDIRFX.O_END_DT = FXCSDRFX.O_END_DT;
        FXRDIRFX.STATUS = FXCSDRFX.STATUS;
    }
    public void B030_OUTPUT_DATA_PROCESS_LST() throws IOException,SQLException,Exception {
        B031_OUTPUT_FORMAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, FXCODRFX);
        SCCMPAG.DATA_LEN = 908;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B031_OUTPUT_FORMAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCODRFX);
        FXCODRFX.TRN_DT = FXRDIRFX.KEY.TRN_DT;
        FXCODRFX.PROD_CD = FXRDIRFX.PROD_CD;
        FXCODRFX.REG_CD = FXRDIRFX.ES_REG_CD;
        FXCODRFX.CI_NO = FXRDIRFX.CI_NO;
        FXCODRFX.TRA_AC = FXRDIRFX.TRA_AC;
        FXCODRFX.CI_CNM = FXRDIRFX.CI_CNM;
        FXCODRFX.CI_ENM = FXRDIRFX.CI_ENM;
        FXCODRFX.BUY_CCY = FXRDIRFX.BUY_CCY;
        FXCODRFX.BUY_AMT = FXRDIRFX.BUY_AMT;
        FXCODRFX.SELL_CCY = FXRDIRFX.SELL_CCY;
        FXCODRFX.SELL_AMT = FXRDIRFX.SELL_AMT;
        FXCODRFX.STATUS = FXRDIRFX.STATUS;
        FXCODRFX.JRN_NO = FXRDIRFX.KEY.JRN_NO;
        FXCODRFX.CTA_NO = FXRDIRFX.CTA_NO;
        FXCODRFX.TIK_NO = FXRDIRFX.TIK_NO;
        FXCODRFX.PAC_FLG = FXRDIRFX.PAC_FLG;
        FXCODRFX.DD_AC1 = FXRDIRFX.DD_AC1;
        FXCODRFX.BUY_SUPAC = FXRDIRFX.BUY_SUPAC;
        FXCODRFX.B_CS_FLG = FXRDIRFX.B_CS_FLG;
        FXCODRFX.CAC_FLG = FXRDIRFX.CAC_FLG;
        FXCODRFX.DD_AC2 = FXRDIRFX.DD_AC2;
        FXCODRFX.SELL_SUPAC = FXRDIRFX.SELL_SUPAC;
        FXCODRFX.S_CS_FLG = FXRDIRFX.S_CS_FLG;
        FXCODRFX.O_END_DT = FXRDIRFX.O_END_DT;
        FXCODRFX.EX_TIME = FXRDIRFX.EX_TIME;
        FXCODRFX.EX_RATE = FXRDIRFX.EX_RATE;
        FXCODRFX.CRT_TLR = FXRDIRFX.CRT_TLR;
        FXCODRFX.UPD_TLR = FXRDIRFX.UPD_TLR;
        IBS.init(SCCGWA, FXRSUINF);
        FXRSUINF.KEY.CTA_NO = FXRDIRFX.CTA_NO;
        FXTSUINF_RD = new DBParm();
        FXTSUINF_RD.TableName = "FXTSUINF";
        IBS.READ(SCCGWA, FXRSUINF, FXTSUINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, FXRSUINF.IMG_NO);
            FXCODRFX.IMG_NO = FXRSUINF.IMG_NO;
        }
    }
    public void B032_QUERY_OUTPUT_FORMAT() throws IOException,SQLException,Exception {
        WS_Q_OUTPUT_DATA.WS_Q_TIK_NO = FXRDIRFX.TIK_NO;
        WS_Q_OUTPUT_DATA.WS_Q_REG_CD = FXRDIRFX.ES_REG_CD;
        WS_Q_OUTPUT_DATA.WS_Q_PROD_CD = FXRDIRFX.PROD_CD;
        WS_Q_OUTPUT_DATA.WS_Q_PROD_NM = FXRDIRFX.PROD_NM;
        WS_Q_OUTPUT_DATA.WS_Q_CI_NO = FXRDIRFX.CI_NO;
        WS_Q_OUTPUT_DATA.WS_Q_CI_CNM = FXRDIRFX.CI_CNM;
        WS_Q_OUTPUT_DATA.WS_Q_CI_ENM = FXRDIRFX.CI_ENM;
        WS_Q_OUTPUT_DATA.WS_Q_CI_TYPE = '1';
        WS_Q_OUTPUT_DATA.WS_Q_TRA_AC = FXRDIRFX.TRA_AC;
        WS_Q_OUTPUT_DATA.WS_Q_ID_TYP = FXRDIRFX.ID_TYP;
        WS_Q_OUTPUT_DATA.WS_Q_ID_NO = FXRDIRFX.ID_NO;
        WS_Q_OUTPUT_DATA.WS_Q_STS = FXRDIRFX.STATUS;
        WS_Q_OUTPUT_DATA.WS_Q_VALUE_DT = FXRDIRFX.VALUE_DT;
        WS_Q_OUTPUT_DATA.WS_Q_O_END_DT = FXRDIRFX.O_END_DT;
        WS_Q_OUTPUT_DATA.WS_Q_PAC_FLG = FXRDIRFX.PAC_FLG;
        WS_Q_OUTPUT_DATA.WS_Q_DD_AC1 = FXRDIRFX.DD_AC1;
        WS_Q_OUTPUT_DATA.WS_Q_BUY_SUPAC = FXRDIRFX.BUY_SUPAC;
        WS_Q_OUTPUT_DATA.WS_Q_B_CS_FLG = FXRDIRFX.B_CS_FLG;
        WS_Q_OUTPUT_DATA.WS_Q_BUY_CCY = FXRDIRFX.BUY_CCY;
        WS_Q_OUTPUT_DATA.WS_Q_BUY_AMT = FXRDIRFX.BUY_AMT;
        WS_Q_OUTPUT_DATA.WS_Q_CAC_FLG = FXRDIRFX.CAC_FLG;
        WS_Q_OUTPUT_DATA.WS_Q_DD_AC2 = FXRDIRFX.DD_AC2;
        WS_Q_OUTPUT_DATA.WS_Q_SELL_SUPAC = FXRDIRFX.SELL_SUPAC;
        WS_Q_OUTPUT_DATA.WS_Q_S_CS_FLG = FXRDIRFX.S_CS_FLG;
        WS_Q_OUTPUT_DATA.WS_Q_SELL_CCY = FXRDIRFX.SELL_CCY;
        WS_Q_OUTPUT_DATA.WS_Q_SELL_AMT = FXRDIRFX.SELL_AMT;
        WS_Q_OUTPUT_DATA.WS_Q_REV_NO = FXRDIRFX.REV_NO;
        WS_Q_OUTPUT_DATA.WS_Q_EX_CODE = FXRDIRFX.EX_CODE;
        WS_Q_OUTPUT_DATA.WS_Q_EX_GROUP = FXRDIRFX.EX_GROUP;
        WS_Q_OUTPUT_DATA.WS_Q_SYS_RATE = FXRDIRFX.SYS_RATE;
        WS_Q_OUTPUT_DATA.WS_Q_PRE_RATE = FXRDIRFX.PRE_RATE;
        WS_Q_OUTPUT_DATA.WS_Q_SPREAD = FXRDIRFX.SPREAD;
        WS_Q_OUTPUT_DATA.WS_Q_EX_RATE = FXRDIRFX.EX_RATE;
        WS_Q_OUTPUT_DATA.WS_Q_RATE_TM = FXRDIRFX.RATE_TM;
        WS_Q_OUTPUT_DATA.WS_Q_EX_TIME = FXRDIRFX.EX_TIME;
        WS_Q_OUTPUT_DATA.WS_Q_REF_NO = FXRDIRFX.REF_NO;
        WS_Q_OUTPUT_DATA.WS_Q_EXST_CD1 = FXRDIRFX.EXST_CD1;
        WS_Q_OUTPUT_DATA.WS_Q_AMT_NS1 = FXRDIRFX.AMT_NS1;
        WS_Q_OUTPUT_DATA.WS_Q_EXST_CD2 = FXRDIRFX.EXST_CD2;
        WS_Q_OUTPUT_DATA.WS_Q_AMT_NS2 = FXRDIRFX.AMT_NS2;
        WS_Q_OUTPUT_DATA.WS_Q_EXST_CD3 = FXRDIRFX.EXST_CD3;
        WS_Q_OUTPUT_DATA.WS_Q_AMT_NS3 = FXRDIRFX.AMT_NS3;
        CEP.TRC(SCCGWA, WS_Q_OUTPUT_DATA.WS_Q_EXST_CD1);
        CEP.TRC(SCCGWA, WS_Q_OUTPUT_DATA.WS_Q_EXST_CD2);
        CEP.TRC(SCCGWA, WS_Q_OUTPUT_DATA.WS_Q_EXST_CD3);
        WS_Q_OUTPUT_DATA.WS_Q_FX_CP_CD = FXRDIRFX.FX_CAP_CD;
        WS_Q_OUTPUT_DATA.WS_Q_FX_TX_CD = FXRDIRFX.FX_TX_CD;
        WS_Q_OUTPUT_DATA.WS_Q_USE_TYPE = FXRDIRFX.SET_USE_TYPE;
        WS_Q_OUTPUT_DATA.WS_Q_USE_DTL = FXRDIRFX.SET_USE_DTL;
        WS_Q_OUTPUT_DATA.WS_Q_CI_NAME = FXRDIRFX.REG_NAME;
        WS_Q_OUTPUT_DATA.WS_Q_CI_TELNO = FXRDIRFX.CI_TELNO;
        WS_Q_OUTPUT_DATA.WS_Q_FAIL_RSN = FXRDIRFX.FAIL_RSN;
        WS_Q_OUTPUT_DATA.WS_Q_BS_TYPE = FXRDIRFX.BS_TYPE;
    }
    public void B040_QUERY_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXRDIRFX);
        IBS.init(SCCGWA, FXCRDRFX);
        FXCRDRFX.FUNC = 'I';
        FXRDIRFX.KEY.TRN_DT = FXCSDRFX.TRN_DT;
        FXRDIRFX.KEY.JRN_NO = FXCSDRFX.JRN_NO;
        CEP.TRC(SCCGWA, FXRDIRFX.KEY.JRN_NO);
        FXCRDRFX.REC_PTR = FXRDIRFX;
        FXCRDRFX.REC_LEN = 2776;
        S000_CALL_FXZRDRFX();
        if (pgmRtn) return;
        if (FXCRDRFX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, FXCRDRFX.RC);
        }
        if (FXCRDRFX.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_NOTFND);
        }
        B070_OUTPUT_DATA_PROCESS_FMT();
        if (pgmRtn) return;
    }
    public void B050_ADD_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        if (FXCSDRFX.O_END_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            if (FXCSDRFX.PAC_FLG == '0' 
                || FXCSDRFX.CAC_FLG == '0') {
                B053_UPD_CASH_BOX();
                if (pgmRtn) return;
            }
            if (FXCSDRFX.PAC_FLG == '1' 
                || FXCSDRFX.CAC_FLG == '1') {
                B052_DR_CR_ACCOUNT();
                if (pgmRtn) return;
            }
            if (FXCSDRFX.PAC_FLG == '2' 
                || FXCSDRFX.CAC_FLG == '2' 
                || FXCSDRFX.PAC_FLG == '3' 
                || FXCSDRFX.CAC_FLG == '3') {
                S054_DRCR_SUS_AC();
                if (pgmRtn) return;
            }
        } else {
            if (FXCSDRFX.PAC_FLG == '1') {
                B020_ADVANCE_HOLD_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCUHLD.DATA.HLD_NO);
                FXCSDRFX.HLD_NO = DCCUHLD.DATA.HLD_NO;
            }
        }
        IBS.init(SCCGWA, FXCPGNFX);
        FXCPGNFX.COMM_DATA.FUNC = 'E';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, FXCSDRFX);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], FXCPGNFX);
        CEP.TRC(SCCGWA, FXCPGNFX.COMM_DATA.DD_AC1);
        CEP.TRC(SCCGWA, FXCPGNFX.COMM_DATA.DD_AC2);
        CEP.TRC(SCCGWA, "DEVHZ");
        CEP.TRC(SCCGWA, FXCSDRFX.DD_AC1);
        CEP.TRC(SCCGWA, FXCSDRFX.DD_AC2);
        S000_CALL_FXZPGNFX();
        if (pgmRtn) return;
        FXCSDRFX.SEQ = FXCPGNFX.COMM_DATA.SEQ;
        FXCSDRFX.CTA_NO = FXCPGNFX.COMM_DATA.CTA_NO;
        FXCSDRFX.STATUS = FXCPGNFX.COMM_DATA.STATUS;
        FXCSDRFX.HLD_NO = FXCPGNFX.COMM_DATA.HLD_NO;
        IBS.init(SCCGWA, FXRSUINF);
        FXRSUINF.KEY.CTA_NO = FXCSDRFX.CTA_NO;
        FXRSUINF.IMG_NO = FXCSDRFX.IMG_NO;
        FXRSUINF.USE_YRMO = FXCSDRFX.USE_YRMO;
        FXRSUINF.USE_TYPE = FXCSDRFX.SUSE_TYP;
        FXRSUINF.USE_INF_01 = FXCSDRFX.USE_INF1;
        FXRSUINF.USE_INF_02 = FXCSDRFX.USE_INF2;
        FXRSUINF.USE_INF_03 = FXCSDRFX.USE_INF3;
        FXRSUINF.USE_INF_04 = FXCSDRFX.USE_INF4;
        FXRSUINF.USE_INF_05 = FXCSDRFX.USE_INF5;
        FXRSUINF.USE_INF_06 = FXCSDRFX.USE_INF6;
        FXRSUINF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        FXRSUINF.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        FXRSUINF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FXRSUINF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FXTSUINF_RD = new DBParm();
        FXTSUINF_RD.TableName = "FXTSUINF";
        IBS.WRITE(SCCGWA, FXRSUINF, FXTSUINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_REC_EXIST);
        }
    }
    public void B052_DR_CR_ACCOUNT() throws IOException,SQLException,Exception {
        if (FXCSDRFX.DD_AC2.trim().length() > 0 
            && !FXCSDRFX.DD_AC2.equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = FXCSDRFX.DD_AC2;
            DDCUCRAC.CCY = FXCSDRFX.SELL_CCY;
            DDCUCRAC.TX_AMT = FXCSDRFX.SELL_AMT;
            CEP.TRC(SCCGWA, FXCSDRFX.S_CS_FLG);
            if (FXCSDRFX.S_CS_FLG == '1') {
                DDCUCRAC.CCY_TYPE = '2';
            }
            if (FXCSDRFX.S_CS_FLG == '0') {
                DDCUCRAC.CCY_TYPE = '1';
            }
            CEP.TRC(SCCGWA, DDCUCRAC.CCY_TYPE);
            CEP.TRC(SCCGWA, FXCSDRFX.PAC_FLG);
            if (FXCSDRFX.PAC_FLG == '0') {
                DDCUCRAC.TX_TYPE = 'C';
            }
            if (FXCSDRFX.PAC_FLG == '1') {
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.OTHER_AC = FXCSDRFX.DD_AC1;
            }
            if (FXCSDRFX.PAC_FLG == '2' 
                || FXCSDRFX.PAC_FLG == '3') {
                DDCUCRAC.TX_TYPE = 'T';
                DDCUCRAC.OTHER_AC = FXCSDRFX.B_SUPAC;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUCRAC.TX_MMO = "G004";
            } else {
                if (FXCSDRFX.PROD_CD.equalsIgnoreCase("1303020403")) {
                    DDCUCRAC.TX_MMO = "Y012";
                } else {
                    DDCUCRAC.TX_MMO = "Y013";
                }
            }
            DDCUCRAC.REMARKS = FXCSDRFX.RMK;
            DDCUCRAC.VAL_DATE = FXCSDRFX.O_END_DT;
            CEP.TRC(SCCGWA, DDCUCRAC.AC);
            CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AC);
            CEP.TRC(SCCGWA, DDCUCRAC.RLT_AC);
            CEP.TRC(SCCGWA, DDCUCRAC.CCY);
            CEP.TRC(SCCGWA, DDCUCRAC.TX_AMT);
            CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
            CEP.TRC(SCCGWA, DDCUCRAC.VAL_DATE);
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (FXCSDRFX.DD_AC1.trim().length() > 0 
            && !FXCSDRFX.DD_AC1.equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = FXCSDRFX.DD_AC1;
            DDCUDRAC.CCY = FXCSDRFX.BUY_CCY;
            DDCUDRAC.TX_AMT = FXCSDRFX.BUY_AMT;
            CEP.TRC(SCCGWA, FXCSDRFX.B_CS_FLG);
            if (FXCSDRFX.B_CS_FLG == '1') {
                DDCUDRAC.CCY_TYPE = '2';
            }
            if (FXCSDRFX.B_CS_FLG == '0') {
                DDCUDRAC.CCY_TYPE = '1';
            }
            CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
            if (FXCSDRFX.CAC_FLG == '0') {
                DDCUDRAC.TX_TYPE = 'C';
            }
            if (FXCSDRFX.CAC_FLG == '1') {
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.OTHER_AC = FXCSDRFX.DD_AC2;
            }
            if (FXCSDRFX.CAC_FLG == '2' 
                || FXCSDRFX.CAC_FLG == '3') {
                DDCUDRAC.TX_TYPE = 'T';
                DDCUDRAC.OTHER_AC = FXCSDRFX.S_SUPAC;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUDRAC.TX_MMO = "G004";
            } else {
                if (FXCSDRFX.PROD_CD.equalsIgnoreCase("1303020403")) {
                    DDCUDRAC.TX_MMO = "Y014";
                } else {
                    DDCUDRAC.TX_MMO = "Y015";
                }
            }
            DDCUDRAC.REMARKS = FXCSDRFX.RMK;
            DDCUDRAC.VAL_DATE = FXCSDRFX.O_END_DT;
            CEP.TRC(SCCGWA, DDCUDRAC.AC);
            CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AC);
            CEP.TRC(SCCGWA, DDCUDRAC.RLT_AC);
            CEP.TRC(SCCGWA, DDCUDRAC.CCY);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
            CEP.TRC(SCCGWA, DDCUDRAC.TX_MMO);
            CEP.TRC(SCCGWA, DDCUDRAC.VAL_DATE);
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
    }
    public void B053_UPD_CASH_BOX() throws IOException,SQLException,Exception {
        if (FXCSDRFX.PAC_FLG == '0') {
            IBS.init(SCCGWA, BPCUABOX);
            BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUABOX.CCY = FXCSDRFX.BUY_CCY;
            BPCUABOX.AMT = FXCSDRFX.BUY_AMT;
            S000_CALL_BPZUABOX();
            if (pgmRtn) return;
        }
        if (FXCSDRFX.CAC_FLG == '0') {
            IBS.init(SCCGWA, BPCUSBOX);
            BPCUSBOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUSBOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUSBOX.CCY = FXCSDRFX.SELL_CCY;
            BPCUSBOX.AMT = FXCSDRFX.SELL_AMT;
            S000_CALL_BPZUSBOX();
            if (pgmRtn) return;
        }
    }
    public void S054_DRCR_SUS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
        JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
        for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
        AICUUPIA.DATA.POST_NARR = "EX MARKET -" + AICUUPIA.DATA.POST_NARR.substring(11);
        if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
        JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
        for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
        if (FXCPGNFX.COMM_DATA.CTA_NO == null) FXCPGNFX.COMM_DATA.CTA_NO = "";
        JIBS_tmp_int = FXCPGNFX.COMM_DATA.CTA_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) FXCPGNFX.COMM_DATA.CTA_NO += " ";
        AICUUPIA.DATA.POST_NARR = AICUUPIA.DATA.POST_NARR.substring(0, 17 - 1) + FXCPGNFX.COMM_DATA.CTA_NO + AICUUPIA.DATA.POST_NARR.substring(17 + 19 - 1);
        AICUUPIA.DATA.RVS_NO = FXCSDRFX.REV_NO;
        CEP.TRC(SCCGWA, FXCPGNFX.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, FXCSDRFX.REV_NO);
        if (FXCSDRFX.PAC_FLG == '2' 
            || FXCSDRFX.PAC_FLG == '3') {
            AICUUPIA.DATA.AMT = FXCSDRFX.BUY_AMT;
            AICUUPIA.DATA.CCY = FXCSDRFX.BUY_CCY;
            AICUUPIA.DATA.VALUE_DATE = FXCSDRFX.O_END_DT;
            AICUUPIA.DATA.AC_NO = FXCSDRFX.B_SUPAC;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
            JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
            for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
            AICUUPIA.DATA.POST_NARR = AICUUPIA.DATA.POST_NARR.substring(0, 12 - 1) + " MAB" + AICUUPIA.DATA.POST_NARR.substring(12 + 4 - 1);
            AICUUPIA.DATA.EVENT_CODE = "DR";
            if (FXCSDRFX.CAC_FLG == '1') {
                AICUUPIA.DATA.THEIR_AC = FXCSDRFX.DD_AC2;
            }
            if (FXCSDRFX.CAC_FLG == '2' 
                || FXCSDRFX.CAC_FLG == '3') {
                AICUUPIA.DATA.THEIR_AC = FXCSDRFX.S_SUPAC;
            }
            AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.THEIR_CCY = FXCSDRFX.SELL_CCY;
            AICUUPIA.DATA.THEIR_AMT = FXCSDRFX.SELL_AMT;
            AICUUPIA.DATA.THEIR_RAT = FXCSDRFX.EX_RATE;
            AICUUPIA.DATA.PAY_MAN = FXCSDRFX.CI_CNM;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        if (FXCSDRFX.CAC_FLG == '2' 
            || FXCSDRFX.CAC_FLG == '3') {
            AICUUPIA.DATA.AMT = FXCSDRFX.SELL_AMT;
            AICUUPIA.DATA.CCY = FXCSDRFX.SELL_CCY;
            AICUUPIA.DATA.VALUE_DATE = FXCSDRFX.O_END_DT;
            AICUUPIA.DATA.AC_NO = FXCSDRFX.S_SUPAC;
            CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
            if (AICUUPIA.DATA.POST_NARR == null) AICUUPIA.DATA.POST_NARR = "";
            JIBS_tmp_int = AICUUPIA.DATA.POST_NARR.length();
            for (int i=0;i<72-JIBS_tmp_int;i++) AICUUPIA.DATA.POST_NARR += " ";
            AICUUPIA.DATA.POST_NARR = AICUUPIA.DATA.POST_NARR.substring(0, 12 - 1) + " MAS" + AICUUPIA.DATA.POST_NARR.substring(12 + 4 - 1);
            AICUUPIA.DATA.EVENT_CODE = "CR";
            if (FXCSDRFX.PAC_FLG == '1') {
                AICUUPIA.DATA.THEIR_AC = FXCSDRFX.DD_AC1;
            }
            if (FXCSDRFX.PAC_FLG == '2' 
                || FXCSDRFX.PAC_FLG == '3') {
                AICUUPIA.DATA.THEIR_AC = FXCSDRFX.B_SUPAC;
            }
            AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
            AICUUPIA.DATA.THEIR_CCY = FXCSDRFX.BUY_CCY;
            AICUUPIA.DATA.THEIR_AMT = FXCSDRFX.BUY_AMT;
            AICUUPIA.DATA.THEIR_RAT = FXCSDRFX.EX_RATE;
            AICUUPIA.DATA.PAY_MAN = FXCSDRFX.CI_CNM;
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        FXCSDRFX.REV_NO = AICUUPIA.DATA.RVS_NO;
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICUUPIA.RC);
        }
    }
    public void B060_REV_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        IBS.init(SCCGWA, FXCPGNFX);
        FXCPGNFX.COMM_DATA.FUNC = 'E';
        FXCPGNFX.COMM_DATA.TRN_DT = FXCSDRFX.TRN_DT;
        FXCPGNFX.COMM_DATA.JRN_NO = FXCSDRFX.JRN_NO;
        S000_CALL_FXZPGNFX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXCSDRFX.STATUS);
        if (FXCSDRFX.STATUS == 'U' 
            || FXCSDRFX.STATUS == 'E') {
            CEP.TRC(SCCGWA, "RHLD");
            B020_ADVANCE_HOLD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUHLD.DATA.HLD_NO);
        }
        if (FXCSDRFX.STATUS == 'C' 
            || FXCSDRFX.STATUS == 'M') {
            if (FXCSDRFX.PAC_FLG == '0' 
                || FXCSDRFX.CAC_FLG == '0') {
                CEP.TRC(SCCGWA, "CASH");
                B053_UPD_CASH_BOX();
                if (pgmRtn) return;
            }
            if (FXCSDRFX.PAC_FLG == '1' 
                || FXCSDRFX.CAC_FLG == '1') {
                CEP.TRC(SCCGWA, "ACT");
                B052_DR_CR_ACCOUNT();
                if (pgmRtn) return;
            }
            if (FXCSDRFX.PAC_FLG == '2' 
                || FXCSDRFX.CAC_FLG == '2' 
                || FXCSDRFX.PAC_FLG == '3' 
                || FXCSDRFX.CAC_FLG == '3') {
                CEP.TRC(SCCGWA, "SUPAC");
                S054_DRCR_SUS_AC();
                if (pgmRtn) return;
            }
            if (FXCSDRFX.STATUS == 'M') {
                B020_RELEASE_HOLD_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CHK_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        WS_DD_AC1 = FXCSDRFX.DD_AC1;
        DDCIQBAL.DATA.AC = WS_DD_AC1;
        if (FXCSDRFX.BUY_CCY.equalsIgnoreCase("156")) {
            WS_CCY_TYP = '1';
        } else {
            if (FXCSDRFX.B_CS_FLG == '0') {
                WS_CCY_TYP = '1';
            } else {
                WS_CCY_TYP = '2';
            }
        }
        DDCIQBAL.DATA.CCY_TYPE = WS_CCY_TYP;
        WS_BUY_CCY = FXCSDRFX.BUY_CCY;
        DDCIQBAL.DATA.CCY = WS_BUY_CCY;
        R000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        IBS.init(SCCGWA, DDRHLD);
        DDTHLD_BR.rp = new DBParm();
        DDTHLD_BR.rp.TableName = "DDTHLD";
        DDTHLD_BR.rp.where = "CARD_NO = :WS_DD_AC1 "
            + "AND CCY = :WS_BUY_CCY "
            + "AND CCY_TYPE = :WS_CCY_TYP "
            + "AND SPR_BR_TYP = '1' "
            + "AND HLD_STS = 'N'";
        IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        while (WS_END_FLG != 'Y') {
            IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "NOTFND");
                WS_END_FLG = 'Y';
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (DDRHLD.HLD_TYP == '1') {
                    WS_END_FLG = 'Y';
                } else {
                    if (DDRHLD.HLD_TYP == '2') {
                        WS_HLD_AMT = WS_HLD_AMT + DDRHLD.HLD_AMT;
                    }
                }
            }
        }
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
        CEP.TRC(SCCGWA, WS_HLD_AMT);
        WS_BAL = DDCIQBAL.DATA.CURR_BAL - WS_HLD_AMT;
        if (WS_BAL > FXCSDRFX.BUY_AMT) {
            WS_RHLD_FLG = 'Y';
        }
    }
    public void B020_ADVANCE_HOLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.AC = FXCSDRFX.DD_AC1;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.HLD_CLS = '9';
        DCCUHLD.DATA.CCY = FXCSDRFX.BUY_CCY;
        if (FXCSDRFX.B_CS_FLG == '0') {
            DCCUHLD.DATA.CCY_TYP = '1';
        }
        if (FXCSDRFX.B_CS_FLG == '1') {
            DCCUHLD.DATA.CCY_TYP = '2';
        }
        DCCUHLD.DATA.AMT = FXCSDRFX.BUY_AMT;
        DCCUHLD.DATA.RMK = "FX T+1/2";
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.MTHS = (short) WK_MONTHS;
        S000_CALL_SCSSCLDT();
        if (pgmRtn) return;
        WS_TEMP_DT = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_TEMP_DT);
        DCCUHLD.DATA.EXP_DT = WS_TEMP_DT;
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
    }
    public void B020_RELEASE_HOLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        CEP.TRC(SCCGWA, FXCSDRFX.HLD_NO);
        DCCURHLD.DATA.HLD_NO = FXCSDRFX.HLD_NO;
        DCCURHLD.DATA.AC = FXCSDRFX.DD_AC1;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.CCY = FXCSDRFX.BUY_CCY;
        if (FXCSDRFX.B_CS_FLG == '0') {
            DCCUHLD.DATA.CCY_TYP = '1';
        }
        if (FXCSDRFX.B_CS_FLG == '1') {
            DCCUHLD.DATA.CCY_TYP = '2';
        }
        DCCURHLD.DATA.RAMT = FXCSDRFX.BUY_AMT;
        DCCURHLD.DATA.HLD_TYP = '2';
        DCCURHLD.DATA.SPR_TYP = '2';
        DCCURHLD.DATA.RMK = "FX T+1/2";
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_DATA_PROCESS_FMT() throws IOException,SQLException,Exception {
        B032_QUERY_OUTPUT_FORMAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "FX153";
        SCCFMT.DATA_PTR = WS_Q_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 1770;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_UPPER_BRANCE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQORG);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
    }
    public void S000_CALL_FXZPGNFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-P-MAIN-GNFX", FXCPGNFX);
    }
    public void S000_CALL_FXZRDRFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-R-MAIN-DRFX", FXCRDRFX);
    }
    public void S000_CALL_BPZUSBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        DDCUDRAC.CHK_PSW_FLG = 'N';
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC   ", DDCUDRAC);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC    ", DDCUCRAC);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX      ", BPCUABOX);
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD, true);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPCMWD.RC);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.TRC(SCCGWA, "SCSSCLDT");
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            WS_MSGID = "SC" + WS_MSGID.substring(2);
            if (WS_MSGID == null) WS_MSGID = "";
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_MSGID += " ";
            JIBS_tmp_str[0] = "" + SCCCLDT.RC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_MSGID = WS_MSGID.substring(0, 3 - 1) + JIBS_tmp_str[0] + WS_MSGID.substring(3 + 4 - 1);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void R000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
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
