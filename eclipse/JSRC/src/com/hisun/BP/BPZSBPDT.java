package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSBPDT {
    boolean pgmRtn = false;
    String CPN_PDTP_INQUIRE = "BP-P-INQ-PRD-TYPE   ";
    String CPN_PDTP_BROWER = "BP-R-BRW-PRD-TYPE   ";
    BPZSBPDT_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSBPDT_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    BPZSBPDT_WS_PRDT_DATA WS_PRDT_DATA = new BPZSBPDT_WS_PRDT_DATA();
    char WS_TBL_CCY_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCRBPDT BPCRBPDT = new BPCRBPDT();
    SCCGWA SCCGWA;
    BPCSBPDT BPCSBPDT;
    public void MP(SCCGWA SCCGWA, BPCSBPDT BPCSBPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBPDT = BPCSBPDT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBPDT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B003_OUTPUT_PTR_REC();
        if (pgmRtn) return;
    }
    public void B001_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 250;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_TS_REC = " ";
    }
