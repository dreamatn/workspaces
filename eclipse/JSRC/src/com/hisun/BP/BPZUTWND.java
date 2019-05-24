package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZUTWND {
    boolean pgmRtn = false;
    String CPN_R_MAINTAIN_TWND = "BP-R-MAINTAIN-TWND";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "COUNTRY WEEKEND INFORNATION MAINTAIN";
    String K_CPY_BPRTHOL = "BPRTWND";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    String WS_WND_DATA = " ";
    BPZUTWND_REDEFINES6 REDEFINES6 = new BPZUTWND_REDEFINES6();
    String WS_WND_DATA_NEW = " ";
    BPZUTWND_REDEFINES11 REDEFINES11 = new BPZUTWND_REDEFINES11();
    String WS_WND_DATA_OLD = " ";
    BPZUTWND_REDEFINES16 REDEFINES16 = new BPZUTWND_REDEFINES16();
    char WS_DATA_CHANG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRTWND BPRTWND = new BPRTWND();
    BPVTWND BPVNTWND = new BPVTWND();
    BPVTWND BPVOTWND = new BPVTWND();
    BPCRTWND BPCRTWND = new BPCRTWND();
    SCCGWA SCCGWA;
    BPCSTWND BPCUTWND;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTWND BPCUTWND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUTWND = BPCUTWND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUTWND return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPVNTWND);
        IBS.init(SCCGWA, BPVOTWND);
        IBS.init(SCCGWA, BPCRTWND);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUTWND.FUNC);
        if (BPCUTWND.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTWND.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTWND.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTWND.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTWND.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCUTWND.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTWND);
        IBS.init(SCCGWA, BPCRTWND);
        BPRTWND.KEY.EFF_DATE = BPCUTWND.KEY.EFF_DATE;
        BPRTWND.KEY.CAL_CD = BPCUTWND.KEY.CAL_CD;
        BPCRTWND.INFO.DATA_LEN = 68;
        CEP.TRC(SCCGWA, BPCRTWND.INFO.DATA_LEN);
        CEP.TRC(SCCGWA, BPRTWND.KEY.EFF_DATE);
        BPCRTWND.INFO.POINTER = BPRTWND;
        BPCRTWND.INFO.FUNC = 'Q';
        S000_CALL_BPZRTWND();
        if (pgmRtn) return;
        if (BPCRTWND.RETURN_INFO == 'N') {
