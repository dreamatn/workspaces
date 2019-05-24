package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIOIP10 {
    String JIBS_tmp_str[] = new String[10];
    String PGM_SCSSCKDT = "SCSSCKDT";
    char WS_COA_CAT = ' ';
    char WS_COA_TYPE = ' ';
    char WS_POST_TYPE = ' ';
    int WS_IDX = 0;
    AIOIP10_WS_T_CTL WS_T_CTL = new AIOIP10_WS_T_CTL();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    AIB0010_AWA_0010 AIB0010_AWA_0010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOIP10 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0010_AWA_0010>");
        AIB0010_AWA_0010 = (AIB0010_AWA_0010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.CPY2CLS(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL, WS_T_CTL);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCT_GRP[1-1].GRP_NO);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCT_GRP[2-1].GRP_NO);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCT_GRP[3-1].GRP_NO);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCT_GRP[4-1].GRP_NO);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCT_GRP[5-1].GRP_NO);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[1-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[2-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[3-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[4-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[5-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[6-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[7-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[8-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[9-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[10-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[11-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[12-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[13-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[14-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[15-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[16-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[17-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[18-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[19-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[20-1].ACCT_CTR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_FLG);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_FR);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_TO);
        if (AIB0010_AWA_0010.ACCTBR[1-1].ACCT_CTR != 0 
            && AIB0010_AWA_0010.ACCTBR[1-1].ACCT_CTR != 0X00) {
            for (WS_IDX = 1; WS_IDX <= 20; WS_IDX += 1) {
                if (AIB0010_AWA_0010.ACCTBR[WS_IDX-1].ACCT_CTR != 0) {
                    B01_CHECK_ACCTBR();
                }
            }
        }
        if (AIB0010_AWA_0010.COA_FLG.trim().length() > 0) {
            for (WS_IDX = 1; WS_IDX <= 5; WS_IDX += 1) {
                if (AIB0010_AWA_0010.COA_OLD[WS_IDX-1].COA_NO.trim().length() > 0 
                    && AIB0010_AWA_0010.COA_OLD[WS_IDX-1].COA_NO.charAt(0) != 0X00) {
                    B02_CHECK_COA();
                }
            }
        }
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_TYPE);
        if (AIB0010_AWA_0010.COA_TYPE != ' ' 
            && AIB0010_AWA_0010.COA_TYPE != 0X00) {
            B03_CHECK_COA_TYPE();
        }
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_CAT);
        if (AIB0010_AWA_0010.COA_CAT != ' ' 
            && AIB0010_AWA_0010.COA_CAT != 0X00) {
            B04_CHECK_COA_CAT();
        }
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.POSTTYP);
        if (AIB0010_AWA_0010.POSTTYP != ' ' 
            && AIB0010_AWA_0010.POSTTYP != 0X00) {
            B05_CHECK_POSTTYP();
        }
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_FR);
        if ((AIB0010_AWA_0010.COA_FR.trim().length() > 0 
            && AIB0010_AWA_0010.COA_FR.charAt(0) != 0X00) 
            && (AIB0010_AWA_0010.COA_TO.trim().length() > 0 
            && AIB0010_AWA_0010.COA_TO.charAt(0) != 0X00)) {
            B07_CHECK_COA();
        }
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ADJ_DATE);
        if (AIB0010_AWA_0010.ADJ_DATE != 0 
            && AIB0010_AWA_0010.ADJ_DATE != 0X00) {
            B08_CHECK_ADJ_DATE();
        }
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.COA_NEW);
        if (AIB0010_AWA_0010.COA_NEW.trim().length() > 0 
            && AIB0010_AWA_0010.COA_NEW.charAt(0) != 0X00) {
            B09_CHECK_COA_NEW();
        }
        CEP.ERR(SCCGWA);
    }
    public void B01_CHECK_ACCTBR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_IDX);
        CEP.TRC(SCCGWA, AIB0010_AWA_0010.ACCTBR[WS_IDX-1].ACCT_CTR);
        if (AIB0010_AWA_0010.ACCTBR[WS_IDX-1].ACCT_CTR != 0 
            && AIB0010_AWA_0010.ACCTBR[WS_IDX-1].ACCT_CTR != 0X00) {
            CEP.TRC(SCCGWA, "INIT");
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, "CHU SHI HUA");
            BPCPQORG.BR = AIB0010_AWA_0010.ACCTBR[WS_IDX-1].ACCT_CTR;
            CEP.TRC(SCCGWA, "CHUANZHI ");
            CEP.TRC(SCCGWA, "CALL QORG");
            S00_CALL_BPZPQORG();
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S00_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B02_CHECK_COA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.COA_FLG = AIB0010_AWA_0010.COA_FLG;
        AICPQITM.INPUT_DATA.NO = AIB0010_AWA_0010.COA_OLD[WS_IDX-1].COA_NO;
        S00_CALL_AIZPQITM();
    }
    public void B03_CHECK_COA_TYPE() throws IOException,SQLException,Exception {
        WS_COA_TYPE = AIB0010_AWA_0010.COA_TYPE;
        if ((WS_COA_TYPE != 'A' 
            && WS_COA_TYPE != 'L' 
            && WS_COA_TYPE != 'C' 
            && WS_COA_TYPE != 'I' 
            && WS_COA_TYPE != 'E' 
            && WS_COA_TYPE != 'D')) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_8060, AIB0010_AWA_0010.COA_TYPE);
        }
    }
    public void B04_CHECK_COA_CAT() throws IOException,SQLException,Exception {
        WS_COA_CAT = AIB0010_AWA_0010.COA_CAT;
        if ((WS_COA_CAT != 'S' 
            && WS_COA_CAT != 'B' 
            && WS_COA_CAT != 'C')) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_8061, AIB0010_AWA_0010.COA_CAT);
        }
    }
    public void B05_CHECK_POSTTYP() throws IOException,SQLException,Exception {
        WS_POST_TYPE = AIB0010_AWA_0010.POSTTYP;
        if ((WS_POST_TYPE != 'R' 
            && WS_POST_TYPE != 'M')) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_8063, AIB0010_AWA_0010.POSTTYP);
        }
    }
    public void B06_CHECK_COA_FR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.COA_FLG = AIB0010_AWA_0010.COA_FLG;
        AICPQITM.INPUT_DATA.NO = AIB0010_AWA_0010.COA_FR;
        S00_CALL_AIZPQITM();
    }
    public void B07_CHECK_COA() throws IOException,SQLException,Exception {
        if (AIB0010_AWA_0010.COA_TO.compareTo(AIB0010_AWA_0010.COA_FR) < 0) {
            CEP.ERRC(SCCGWA, AICMSG_ERROR_MSG.AI_8065, AIB0010_AWA_0010.COA_FR);
        }
    }
    public void B08_CHECK_ADJ_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = AIB0010_AWA_0010.ADJ_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCKDT ERROR*" + PGM_SCSSCKDT;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B09_CHECK_COA_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.COA_FLG = AIB0010_AWA_0010.COA_FLG;
        AICPQITM.INPUT_DATA.NO = AIB0010_AWA_0010.COA_NEW;
        S00_CALL_AIZPQITM();
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
