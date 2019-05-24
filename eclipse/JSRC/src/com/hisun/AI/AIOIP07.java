package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOIP07 {
    String JIBS_tmp_str[] = new String[10];
    double K_MAX_AMT = 9999999999999999.99;
    double K_MIN_AMT = -9999999999999999.99;
    short WS_CUR_POS = 0;
    long WS_TRC_AMT = 0;
    AIOIP07_WS_T_CTL WS_T_CTL = new AIOIP07_WS_T_CTL();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    AIB0007_AWA_0007 AIB0007_AWA_0007;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOIP07 return!");
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
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0007_AWA_0007>");
        AIB0007_AWA_0007 = (AIB0007_AWA_0007) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.BR_FR);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.BR_TO);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.FLAG);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.COANO);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.ACCENTRE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.CURCODE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.TBEGDATE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.TENDDATE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.VBEGDATE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.VENDDATE);
        if (AIB0007_AWA_0007.BR_FR_NO != 0 
            && AIB0007_AWA_0007.BR_FR > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = AIB0007_AWA_0007.BR_FR;
            WS_CUR_POS = AIB0007_AWA_0007.BR_FR_NO;
            S00_CALL_BPZPQORG();
        } else {
            AIB0007_AWA_0007.BR_FR = 0;
        }
        if (AIB0007_AWA_0007.BR_TO_NO != 0 
            && AIB0007_AWA_0007.BR_TO > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = AIB0007_AWA_0007.BR_TO;
            WS_CUR_POS = AIB0007_AWA_0007.BR_TO_NO;
            S00_CALL_BPZPQORG();
        } else {
            AIB0007_AWA_0007.BR_TO = 0;
        }
        if (AIB0007_AWA_0007.BR_FR != 0 
            && AIB0007_AWA_0007.BR_TO != 0) {
            if (AIB0007_AWA_0007.BR_FR > AIB0007_AWA_0007.BR_TO) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_BRFR_LT_BRTO, AIB0007_AWA_0007.BR_FR_NO);
            }
        }
        if (AIB0007_AWA_0007.BR_FR != 0 
            && AIB0007_AWA_0007.BR_TO == 0) {
            AIB0007_AWA_0007.BR_TO = AIB0007_AWA_0007.BR_FR;
        }
        if (AIB0007_AWA_0007.BR_FR == 0 
            && AIB0007_AWA_0007.BR_TO != 0) {
            AIB0007_AWA_0007.BR_FR = AIB0007_AWA_0007.BR_TO;
        }
        if (AIB0007_AWA_0007.BR_FR == 0 
            && AIB0007_AWA_0007.BR_TO == 0) {
            AIB0007_AWA_0007.BR_FR = 0;
            AIB0007_AWA_0007.BR_TO = 999999;
        }
        if (AIB0007_AWA_0007.ACCENTRE > 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = AIB0007_AWA_0007.ACCENTRE;
            WS_CUR_POS = AIB0007_AWA_0007.ACCENTRE_NO;
            S00_CALL_BPZPQORG();
        } else {
            AIB0007_AWA_0007.ACCENTRE = 0;
        }
        if (AIB0007_AWA_0007.BR_FR == 0 
            && AIB0007_AWA_0007.BR_TO == 0) {
            CEP.TRC(SCCGWA, "1111111111111111111");
            if (AIB0007_AWA_0007.COANO.equalsIgnoreCase("0") 
                || AIB0007_AWA_0007.COANO.charAt(0) == 0X00) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_COA_OR_BR_MUST_INPUT, AIB0007_AWA_0007.COANO_NO);
            }
        }
        CEP.TRC(SCCGWA, "2222222222222222222");
        if (AIB0007_AWA_0007.CURCODE.charAt(0) != 0X00 
            && AIB0007_AWA_0007.CURCODE.trim().length() > 0 
            && !AIB0007_AWA_0007.CURCODE.equalsIgnoreCase("999")) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = AIB0007_AWA_0007.CURCODE;
            WS_CUR_POS = AIB0007_AWA_0007.CURCODE_NO;
            S00_CALL_BPZQCCY();
        } else {
            AIB0007_AWA_0007.CURCODE = " ";
        }
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.TBEGDATE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.TENDDATE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.VBEGDATE);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.VENDDATE);
        if (AIB0007_AWA_0007.TBEGDATE == 0 
            && AIB0007_AWA_0007.TENDDATE == 0 
            && AIB0007_AWA_0007.VBEGDATE == 0 
            && AIB0007_AWA_0007.VENDDATE == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_DATE_MUST_INPUT_ONE, AIB0007_AWA_0007.TBEGDATE_NO);
        }
        if (AIB0007_AWA_0007.TBEGDATE != 0 
            && AIB0007_AWA_0007.TENDDATE == 0) {
            AIB0007_AWA_0007.TENDDATE = AIB0007_AWA_0007.TBEGDATE;
        }
        if (AIB0007_AWA_0007.TBEGDATE == 0 
            && AIB0007_AWA_0007.TENDDATE != 0) {
            AIB0007_AWA_0007.TBEGDATE = SCCGWA.COMM_AREA.TR_DATE;
        }
        if (AIB0007_AWA_0007.TBEGDATE != 0 
            && AIB0007_AWA_0007.TENDDATE != 0) {
            if (AIB0007_AWA_0007.TBEGDATE > AIB0007_AWA_0007.TENDDATE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.BEGIN_DT_GT_END_DT, AIB0007_AWA_0007.TBEGDATE_NO);
            }
        }
        if (AIB0007_AWA_0007.VBEGDATE != 0 
            && AIB0007_AWA_0007.VENDDATE == 0) {
            AIB0007_AWA_0007.VENDDATE = AIB0007_AWA_0007.VBEGDATE;
        }
        if (AIB0007_AWA_0007.VBEGDATE == 0 
            && AIB0007_AWA_0007.VENDDATE != 0) {
            AIB0007_AWA_0007.VBEGDATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (AIB0007_AWA_0007.VBEGDATE != 0 
            && AIB0007_AWA_0007.VENDDATE != 0) {
            if (AIB0007_AWA_0007.VBEGDATE > AIB0007_AWA_0007.VENDDATE) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.R_BEGIN_DT_GT_END_DT, AIB0007_AWA_0007.VBEGDATE_NO);
            }
        }
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.BEGAMT);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.ENDAMT);
        WS_TRC_AMT = (long) AIB0007_AWA_0007.BEGAMT;
        CEP.TRC(SCCGWA, WS_TRC_AMT);
        if (AIB0007_AWA_0007.BEGAMT != 0 
            && AIB0007_AWA_0007.ENDAMT == 0) {
            CEP.TRC(SCCGWA, "1111111111");
        }
        if (AIB0007_AWA_0007.BEGAMT == 0 
            && AIB0007_AWA_0007.ENDAMT != 0) {
            CEP.TRC(SCCGWA, "2222222222");
        }
        if (AIB0007_AWA_0007.BEGAMT != 0 
            && AIB0007_AWA_0007.ENDAMT != 0) {
            if (AIB0007_AWA_0007.BEGAMT > AIB0007_AWA_0007.ENDAMT) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_BEG_AMT_GT_END_AMT, AIB0007_AWA_0007.BEGAMT_NO);
            }
        }
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.BEGAMT);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.ENDAMT);
        CEP.TRC(SCCGWA, AIB0007_AWA_0007.REFNO);
        if (AIB0007_AWA_0007.DRCRIND == ' ' 
                && AIB0007_AWA_0007.DRCRIND == 0X00) {
            AIB0007_AWA_0007.DRCRIND = ' ';
        } else if (AIB0007_AWA_0007.REFNO.trim().length() == 0 
                && AIB0007_AWA_0007.REFNO.charAt(0) == 0X00) {
            AIB0007_AWA_0007.REFNO = " ";
            CEP.TRC(SCCGWA, AIB0007_AWA_0007.REFNO);
        } else if (AIB0007_AWA_0007.PORCODE.trim().length() == 0 
                && AIB0007_AWA_0007.PORCODE.charAt(0) == 0X00) {
            AIB0007_AWA_0007.PORCODE = " ";
        } else if (AIB0007_AWA_0007.EVENCODE.trim().length() == 0 
                && AIB0007_AWA_0007.EVENCODE.charAt(0) == 0X00) {
            AIB0007_AWA_0007.EVENCODE = " ";
        } else if (AIB0007_AWA_0007.TRNTYPE.trim().length() == 0 
                && AIB0007_AWA_0007.TRNTYPE.charAt(0) == 0X00) {
            AIB0007_AWA_0007.TRNTYPE = " ";
        } else if (AIB0007_AWA_0007.CINO.trim().length() == 0 
                && AIB0007_AWA_0007.CINO.charAt(0) == 0X00) {
            AIB0007_AWA_0007.CINO = " ";
        } else if (AIB0007_AWA_0007.CONTNO.trim().length() == 0 
                && AIB0007_AWA_0007.CONTNO.charAt(0) == 0X00) {
            AIB0007_AWA_0007.CONTNO = " ";
        } else if (AIB0007_AWA_0007.CONTTYPE.trim().length() == 0 
                && AIB0007_AWA_0007.CONTTYPE.charAt(0) == 0X00) {
            AIB0007_AWA_0007.CONTTYPE = " ";
        } else if (AIB0007_AWA_0007.BEGAMT == 0 
                && AIB0007_AWA_0007.ENDAMT == 0) {
            AIB0007_AWA_0007.BEGAMT = 0;
            AIB0007_AWA_0007.ENDAMT = 0;
        } else {
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_CUR_POS);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
