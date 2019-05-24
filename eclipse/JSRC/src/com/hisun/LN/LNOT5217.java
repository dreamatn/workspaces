package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5217 {
    int JIBS_tmp_int;
    DBParm LNTSCHT_RD;
    DBParm LNTCONT_RD;
    String JIBS_tmp_str[] = new String[10];
    LNOT5217_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT5217_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    LNCCMSCH LNCCMSCH = new LNCCMSCH();
    LNRCONT LNRCONT = new LNRCONT();
    LNRSCHT LNRSCHT = new LNRSCHT();
    SCCGWA SCCGWA;
    LNB5210_AWA_5210 LNB5210_AWA_5210;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT5217 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5210_AWA_5210>");
        LNB5210_AWA_5210 = (LNB5210_AWA_5210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_SVR_PROCESS();
        B300_AUTH_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.TRAN_SEQ);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CTA_NO);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.REPY_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PRD_TYP);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CCY);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.PRIN_AMT);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.OS_BAL);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.CI_CNM);
        if (LNB5210_AWA_5210.CTA_NO.compareTo(SPACE) <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNB5210_AWA_5210.CTA_NO;
        T000_READ_LNTCONT();
        IBS.init(SCCGWA, LNRSCHT);
        if (LNB5210_AWA_5210.CTA_NO.trim().length() == 0) LNRSCHT.KEY.CONTRACT_NO = 0;
        else LNRSCHT.KEY.CONTRACT_NO = Integer.parseInt(LNB5210_AWA_5210.CTA_NO);
        LNRSCHT.KEY.SUB_CTA_NO = "" + 0;
        JIBS_tmp_int = LNRSCHT.KEY.SUB_CTA_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNRSCHT.KEY.SUB_CTA_NO = "0" + LNRSCHT.KEY.SUB_CTA_NO;
        LNRSCHT.KEY.TRAN_SEQ = LNB5210_AWA_5210.TRAN_SEQ;
        LNRSCHT.KEY.TYPE = 'P';
        T000_READ_LNTSCHT();
        LNRSCHT.KEY.TYPE = 'I';
        T000_READ_LNTSCHT();
    }
    public void B200_CALL_SVR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCMSCH);
        LNCCMSCH.COMM_DATA.SEQ_NO = LNB5210_AWA_5210.TRAN_SEQ;
        LNCCMSCH.COMM_DATA.CTA_NO = LNB5210_AWA_5210.CTA_NO;
        LNCCMSCH.COMM_DATA.REPY_TYP = LNB5210_AWA_5210.REPY_TYP;
        LNCCMSCH.COMM_DATA.PROD_CD = LNB5210_AWA_5210.PRD_TYP;
        LNCCMSCH.COMM_DATA.CCY = LNB5210_AWA_5210.CCY;
        LNCCMSCH.COMM_DATA.PRIN_AMT = LNB5210_AWA_5210.PRIN_AMT;
        LNCCMSCH.COMM_DATA.OS_BAL = 0;
        LNCCMSCH.COMM_DATA.OS_BAL = LNB5210_AWA_5210.OS_BAL;
        LNCCMSCH.COMM_DATA.CI_CNM = LNB5210_AWA_5210.CI_CNM;
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.REPY_TYP);
        CEP.TRC(SCCGWA, LNCCMSCH.COMM_DATA.SEQ_NO);
        CEP.TRC(SCCGWA, LNCCMSCH.COMM_DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCCMSCH.COMM_DATA.OS_BAL);
        CEP.TRC(SCCGWA, LNB5210_AWA_5210.OS_BAL);
        S000_CALL_SVR_LNZCMSCH();
    }
    public void B300_AUTH_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_SVR_LNZCMSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CONFIRM-SCHE", LNCCMSCH);
    }
    public void T000_READ_LNTSCHT() throws IOException,SQLException,Exception {
        LNTSCHT_RD = new DBParm();
        LNTSCHT_RD.TableName = "LNTSCHT";
        LNTSCHT_RD.eqWhere = "TRAN_SEQ,CONTRACT_NO,SUB_CTA_NO,TYPE";
        LNTSCHT_RD.fst = true;
        LNTSCHT_RD.order = "TERM DESC";
        IBS.READ(SCCGWA, LNRSCHT, LNTSCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && LNRSCHT.DUE_DTE != LNRCONT.MAT_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PLAN_NOT_CPLT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.CONT_NFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
