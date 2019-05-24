package com.hisun.LN;

import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICLNQ {
    int JIBS_tmp_int;
    DBParm LNTCONT_RD;
    DBParm LNTLOAN_RD;
    DBParm LNTICTL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_LNZICLNQ = "LNZICLNQ";
    String K_CLDD = "CLDD";
    String K_CLGU = "CLGU";
    String K_CLDL = "CLDL";
    String K_CLDP = "CLDP";
    String WS_ERR_MSG = " ";
    String WS_OUTPUT_FMT = " ";
    char WS_STS = ' ';
    double WS_TOT_BAL = 0;
    char WS_NOTFND_FLAG = ' ';
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNRCONT LNRCONT = new LNRCONT();
    CICACCU CICACCU = new CICACCU();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCICSTS LNCICSTS = new LNCICSTS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNRICTL LNRICTL = new LNRICTL();
    LNRLOAN LNRLOAN = new LNRLOAN();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    SCCGWA SCCGWA;
    LNCCLNQ LNCCLNQ;
    public void MP(SCCGWA SCCGWA, LNCCLNQ LNCCLNQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCLNQ = LNCCLNQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICLNQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCCLNQ.RC.RC_MMO = "LN";
        LNCCLNQ.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHECK() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_READ_CONT_INFO();
        if (pgmRtn) return;
        R000_READ_ICTL_INFO();
        if (pgmRtn) return;
        R000_READ_LOAN_INFO();
        if (pgmRtn) return;
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLGU)) {
            R000_GEN_LOAN_STS();
            if (pgmRtn) return;
        }
        if (LNCCLNQ.PROD_FLG == 'Y') {
            R000_GEN_PROD_INFO();
            if (pgmRtn) return;
        }
        if (LNCCLNQ.FUNC == '0') {
        } else if (LNCCLNQ.FUNC == '1') {
            R000_READ_AMT_INFO();
            if (pgmRtn) return;
        } else if (LNCCLNQ.FUNC == '2') {
            R000_READ_CINO_INFO();
            if (pgmRtn) return;
        } else if (LNCCLNQ.FUNC == '3') {
            R000_READ_AMT_INFO();
            if (pgmRtn) return;
            R000_READ_CINO_INFO();
            if (pgmRtn) return;
        } else if (LNCCLNQ.FUNC == '4') {
            R000_LOAN_DC_STS();
            if (pgmRtn) return;
            R000_READ_AMT_INFO();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_READ_CONT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_NO);
        LNRCONT.KEY.CONTRACT_NO = LNCCLNQ.DATA.CONT_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        LNCCLNQ.DATA.CONT_TYPE = LNRCONT.CONTRACT_TYPE;
        LNCCLNQ.DATA.FATHER_CONTRACT = LNRCONT.FATHER_CONTRACT;
        LNCCLNQ.DATA.PROD_CD = LNRCONT.PROD_CD;
        LNCCLNQ.DATA.CCY = LNRCONT.CCY;
        LNCCLNQ.DATA.VAL_DT = LNRCONT.START_DATE;
        LNCCLNQ.DATA.DUE_DT = LNRCONT.MAT_DATE;
        LNCCLNQ.DATA.BOOK_BR = LNRCONT.BOOK_BR;
        LNCCLNQ.DATA.DOMI_BR = LNRCONT.DOMI_BR;
        LNCCLNQ.DATA.LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNCCLNQ.DATA.AMT = LNRCONT.LN_TOT_AMT;
        LNCCLNQ.DATA.TOT_AMT = LNRCONT.ORIG_TOT_AMT;
        LNCCLNQ.DATA.LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.DOMI_BR);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PROD_CD);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CCY);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.AMT);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.DUE_DT);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.FATHER_CONTRACT);
    }
    public void R000_READ_ICTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLGU)) {
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_NO);
            LNRICTL.KEY.CONTRACT_NO = LNCCLNQ.DATA.CONT_NO;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            T000_READ_LNTICTL();
            if (pgmRtn) return;
            LNCCLNQ.DATA.CTL_STSW = LNRICTL.CTL_STSW;
            LNCCLNQ.DATA.IC_CAL_VAL_DT = LNRICTL.IC_CAL_VAL_DT;
            LNCCLNQ.DATA.IC_CAL_DUE_DT = LNRICTL.IC_CAL_DUE_DT;
            LNCCLNQ.DATA.INT_CUT_DT = LNRICTL.INT_CUT_DT;
            LNCCLNQ.DATA.CUR_RAT = LNRICTL.CUR_RAT;
            LNCCLNQ.DATA.CUR_PO_RAT = LNRICTL.CUR_PO_RAT;
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.CUR_RAT);
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.CUR_PO_RAT);
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.CTL_STSW);
        }
    }
    public void R000_READ_LOAN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLDD)) {
            LNRLOAN.KEY.CONTRACT_NO = LNCCLNQ.DATA.CONT_NO;
            T000_READ_LNTLOAN();
            if (pgmRtn) return;
            LNCCLNQ.DATA.PD_PROJ_NO = LNRLOAN.PD_PROJ_NO;
        }
    }
    public void R000_READ_AMT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_NO);
        if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase(K_CLGU)) {
            R000_INQ_NOR_BAL();
            if (pgmRtn) return;
            LNCCLNQ.DATA.N1_BAL = LNCCNEX.COMM_DATA.CTNR_DATA[1-1].CTNR_AMT;
            LNCCLNQ.DATA.N2_BAL = LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT;
            LNCCLNQ.DATA.N_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.N1_BAL);
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.N2_BAL);
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.N_BAL);
            R000_INQ_OVR_BAL();
            if (pgmRtn) return;
            LNCCLNQ.DATA.O_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.O_BAL);
        }
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.N_BAL);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.O_BAL);
        WS_TOT_BAL = LNCCLNQ.DATA.N_BAL + LNCCLNQ.DATA.O_BAL;
        CEP.TRC(SCCGWA, "444444");
        CEP.TRC(SCCGWA, WS_TOT_BAL);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("1")) {
            LNCCLNQ.DATA.TOT_BAL = WS_TOT_BAL;
        } else {
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                LNCCLNQ.DATA.TOT_BAL = 0;
            } else {
                LNCCLNQ.DATA.TOT_BAL = LNRCONT.LN_TOT_AMT;
            }
        }
        CEP.TRC(SCCGWA, "555555");
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.TOT_BAL);
        LNCCLNQ.DATA.PRIN = LNRCONT.ORIG_TOT_AMT;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PRIN);
        LNCCLNQ.DATA.PAY_AMT = LNCCLNQ.DATA.PRIN - LNCCLNQ.DATA.TOT_BAL;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.PAY_AMT);
    }
    public void R000_INQ_NOR_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.SUB_CONT_NO);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQNORP";
        LNCCNEX.COMM_DATA.LN_AC = LNCCLNQ.DATA.CONT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = LNCCLNQ.DATA.SUB_CONT_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void R000_INQ_OVR_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.SUB_CONT_NO);
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQOVDP";
        LNCCNEX.COMM_DATA.LN_AC = LNCCLNQ.DATA.CONT_NO;
        LNCCNEX.COMM_DATA.SUF_NO = LNCCLNQ.DATA.SUB_CONT_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
    }
    public void R000_READ_CINO_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CONT_NO);
        LNRAGRE.KEY.CONTRACT_NO = LNCCLNQ.DATA.CONT_NO;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRAGRE);
        CEP.TRC(SCCGWA, LNRAGRE.PAPER_NO);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = LNRAGRE.PAPER_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        LNCCLNQ.DATA.CI_NO = CICACCU.DATA.CI_NO;
        LNCCLNQ.DATA.CI_CNAME = CICACCU.DATA.CI_CNM;
        LNCCLNQ.DATA.CI_ENAME = CICACCU.DATA.CI_ENM;
        LNCCLNQ.DATA.CI_ATTR = CICACCU.DATA.CI_ATTR;
        LNCCLNQ.DATA.CI_TYP = CICACCU.DATA.CI_TYP;
        LNCCLNQ.DATA.ID_TYPE = CICACCU.DATA.ID_TYPE;
        LNCCLNQ.DATA.IDNO = CICACCU.DATA.ID_NO;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_NO);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_CNAME);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_ENAME);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.CI_TYP);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.IDNO);
    }
    public void R000_GEN_LOAN_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICSTS);
        LNCICSTS.COMM_DATA.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNCICSTS.COMM_DATA.CONTRACT_NO);
        S000_CALL_LNZICSTS();
        if (pgmRtn) return;
        LNCCLNQ.DATA.STS = LNCICSTS.COMM_DATA.STS;
    }
    public void R000_LOAN_DC_STS() throws IOException,SQLException,Exception {
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNCCLNQ.DATA.STS == 'A' 
            && LNRICTL.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
            LNCCLNQ.DATA.STS = 'Z';
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNCCLNQ.DATA.STS == 'P' 
            && LNRICTL.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
            LNCCLNQ.DATA.STS = 'G';
        }
    }
    public void R000_GEN_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = '0';
        LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
        CEP.TRC(SCCGWA, LNRCONT.PROD_CD);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        LNCCLNQ.DATA.PROD_NAME = LNCSCKPD.PROD_NM;
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.CONT_NFND, LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_NOTFND, LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ICTL_NOTFND, LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-GET-ICTL-STS", LNCICSTS);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCLNQ.RC);
            Z_RET();
            if (pgmRtn) return;
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCLNQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCCLNQ=");
            CEP.TRC(SCCGWA, LNCCLNQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
