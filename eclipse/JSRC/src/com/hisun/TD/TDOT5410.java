package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCSGSEQ;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class TDOT5410 {
    boolean pgmRtn = false;
    String K_TD_KD_SEQ = "KDSEQ";
    String K_OUTPUT_FMT1 = "TD410";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    long WS_DOCU_NO = 0;
    String WS_DOCU_NO_X = " ";
    char WS_UPDATE_FLG = ' ';
    char WS_DOCU_FLG = ' ';
    char WS_DEL_FLG = ' ';
    char WS_DUP_FLG = ' ';
    char WS_EFF_TYP = ' ';
    short WS_TIME = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    TDRDOCU TDRDOCU = new TDRDOCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    TDCDOCU TDCDOCU = new TDCDOCU();
    SCCGWA SCCGWA;
    TDB5410_AWA_5410 TDB5410_AWA_5410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT5410 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5410_AWA_5410>");
        TDB5410_AWA_5410 = (TDB5410_AWA_5410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "---TEST---START");
        CEP.TRC(SCCGWA, TDB5410_AWA_5410);
