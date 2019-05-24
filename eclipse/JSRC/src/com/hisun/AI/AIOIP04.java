package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOIP04 {
    String CPN_AI_P_INQ_ITM = "AI-P-INQ-ITM        ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    String CPN_BP_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_BP_P_QUERY_BKAI = "BP-P-QUERY-BKAI     ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_CCY_GRP_TYP = "CCYGP";
    String K_AC_CENTER_GRP_TYP = "ACCGP";
    char K_ERROR = '.';
    char K_MONITOR = 'M';
    double K_MAX_AMT = 9999999999999999.99;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    short WS_K = 0;
    short WS_CUR_POS = 0;
    AIOIP04_WS_T_CTL WS_T_CTL = new AIOIP04_WS_T_CTL();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    AIB0004_AWA_0004 AIB0004_AWA_0004;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOIP04 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0004_AWA_0004>");
        AIB0004_AWA_0004 = (AIB0004_AWA_0004) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.COANO);
        CEP.TRC(SCCGWA, "****************************");
        if (!AIB0004_AWA_0004.COANO.equalsIgnoreCase("0") 
            && AIB0004_AWA_0004.COANO.charAt(0) != 0X00) {
            IBS.init(SCCGWA, AICPQITM);
            CEP.TRC(SCCGWA, AIB0004_AWA_0004.FLAG);
            AICPQITM.INPUT_DATA.BOOK_FLG = AIB0004_AWA_0004.FLAG;
            AICPQITM.INPUT_DATA.NO = AIB0004_AWA_0004.COANO;
            WS_CUR_POS = AIB0004_AWA_0004.COANO_NO;
            S00_CALL_AIZPQITM();
            CEP.TRC(SCCGWA, "****************************");
            CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
            CEP.TRC(SCCGWA, "****************************");
        } else {
            AIB0004_AWA_0004.COANO = " ";
        }
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.BR_FR);
        if (AIB0004_AWA_0004.BR_FR_NO != 0 
            && AIB0004_AWA_0004.BR_FR != 0 
            && AIB0004_AWA_0004.BR_FR != 0X00) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = AIB0004_AWA_0004.BR_FR;
            WS_CUR_POS = AIB0004_AWA_0004.BR_FR_NO;
            S00_CALL_BPZPQORG();
        } else {
            AIB0004_AWA_0004.BR_FR = 0;
        }
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.BR_TO);
        if (AIB0004_AWA_0004.BR_TO_NO != 0 
            && AIB0004_AWA_0004.BR_TO != 0 
            && AIB0004_AWA_0004.BR_TO != 0X00) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = AIB0004_AWA_0004.BR_TO;
            WS_CUR_POS = AIB0004_AWA_0004.BR_TO_NO;
            S00_CALL_BPZPQORG();
        } else {
            AIB0004_AWA_0004.BR_TO = 0;
        }
        if (AIB0004_AWA_0004.BR_FR != 0 
            && AIB0004_AWA_0004.BR_TO != 0) {
            if (AIB0004_AWA_0004.BR_FR > AIB0004_AWA_0004.BR_TO) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BRFR_LT_BRTO;
                WS_FLD_NO = AIB0004_AWA_0004.BR_FR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (AIB0004_AWA_0004.BR_FR != 0 
            && AIB0004_AWA_0004.BR_TO == 0) {
            AIB0004_AWA_0004.BR_TO = AIB0004_AWA_0004.BR_FR;
        }
        if (AIB0004_AWA_0004.BR_FR == 0 
            && AIB0004_AWA_0004.BR_TO != 0) {
            AIB0004_AWA_0004.BR_FR = AIB0004_AWA_0004.BR_TO;
        }
        if (AIB0004_AWA_0004.BR_FR == 0 
            && AIB0004_AWA_0004.BR_TO == 0) {
            AIB0004_AWA_0004.BR_TO = 999999;
        }
        if (AIB0004_AWA_0004.BR_FR == 0 
            && AIB0004_AWA_0004.BR_TO == 0) {
            AIB0004_AWA_0004.BR_FR = 0;
            AIB0004_AWA_0004.BR_TO = 999999;
        }
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACCENTRE);
        CEP.TRC(SCCGWA, "****************************");
        if (AIB0004_AWA_0004.ACCENTRE_NO != 0 
            && AIB0004_AWA_0004.ACCENTRE != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB0004_AWA_0004.ACCENTRE;
            WS_CUR_POS = AIB0004_AWA_0004.ACCENTRE_NO;
            S00_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, "****************************");
            CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
            CEP.TRC(SCCGWA, "****************************");
        } else {
            AIB0004_AWA_0004.ACCENTRE = 0;
        }
        if (AIB0004_AWA_0004.CURCODE_NO != 0 
            && AIB0004_AWA_0004.CURCODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AIB0004_AWA_0004.CURCODE;
            WS_CUR_POS = AIB0004_AWA_0004.CURCODE_NO;
            S00_CALL_BPZQCCY();
        } else {
            AIB0004_AWA_0004.CURCODE = " ";
        }
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACENAME);
        if (AIB0004_AWA_0004.ACENAME.trim().length() == 0 
            || AIB0004_AWA_0004.ACENAME.charAt(0) == 0X00) {
            AIB0004_AWA_0004.ACENAME = " ";
        }
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACENAME);
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACCNAME);
        if (AIB0004_AWA_0004.ACCNAME.trim().length() == 0 
            || AIB0004_AWA_0004.ACCNAME.charAt(0) == 0X00) {
            AIB0004_AWA_0004.ACCNAME = " ";
        }
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.ACCNAME);
        if (AIB0004_AWA_0004.AMT_FR_NO != 0 
            && AIB0004_AWA_0004.AMT_FR != 0 
            && AIB0004_AWA_0004.AMT_TO == 0) {
            CEP.TRC(SCCGWA, AIB0004_AWA_0004.AMT_TO);
        }
        if (AIB0004_AWA_0004.AMT_TO == 0) {
            CEP.TRC(SCCGWA, "DEFAULT VALUE");
            AIB0004_AWA_0004.AMT_TO = 999999999999999.99;
        }
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.AMT_TO);
        if (AIB0004_AWA_0004.AMT_FR_NO != 0 
            && AIB0004_AWA_0004.AMT_FR != 0 
            && AIB0004_AWA_0004.AMT_TO_NO != 0 
            && AIB0004_AWA_0004.AMT_TO != 0) {
            if (AIB0004_AWA_0004.AMT_FR > AIB0004_AWA_0004.AMT_TO) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BEG_AMT_GT_END_AMT;
                WS_FLD_NO = AIB0004_AWA_0004.AMT_FR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, AIB0004_AWA_0004.DATE);
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, "****************************");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, "****************************");
        if (AIB0004_AWA_0004.DATE_NO != 0 
            && AIB0004_AWA_0004.DATE != 0 
            && AIB0004_AWA_0004.DATE != 0X00) {
            if (AIB0004_AWA_0004.DATE > SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
                WS_FLD_NO = AIB0004_AWA_0004.DATE_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            AIB0004_AWA_0004.DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void S00_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            WS_FLD_NO = WS_CUR_POS;
            WS_ERR_INFO = " ";
            WS_ERR_INFO = "T" + ",PLEASE CHECK INPUT DATA";
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            WS_FLD_NO = WS_CUR_POS;
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = WS_CUR_POS;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
