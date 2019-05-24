package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCEXCP_FILLER47;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCIGWA;
import com.hisun.SC.SCCITRC;
import com.hisun.SC.SCCSCHM;
import com.hisun.SC.SCZIGWA;
import com.hisun.SC.SCZITRC;
import com.hisun.SC.SCZSCHM;

public class SOOECI {
    boolean pgmRtn = false;
    String SCZSCHM = "SCZSCHM";
    String SCZIGWA = "SCZIGWA";
    String SCZITRC = "SCZITRC";
    String SOOENTR = "SOOENTR";
    SOOECI_WS_VARS WS_VARS = new SOOECI_WS_VARS();
    SCCIGWA SCCIGWA = new SCCIGWA();
    SCCITRC SCCITRC = new SCCITRC();
    SCCSCHM SCCSCHM = new SCCSCHM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCEXCP_FILLER47 FILLER47 = new SCCEXCP_FILLER47();
    String COMM = " ";
    SOOECI_LK_SCCGWA LK_SCCGWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOOECI return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARS);
        IBS.init(SCCGWA, SCCIGWA);
        SCZIGWA SCZIGWA1 = new SCZIGWA();
        SCZIGWA1.MP(SCCGWA, SCCIGWA);
        LK_SCCGWA = (SOOECI_LK_SCCGWA) SCCIGWA.GWA_PTR;
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_VARS.BEGIN_TS = JIBS_sdf.format(JIBS_date);
        WS_VARS.I = 309848;
        if (SOOECI_LS.COMM == null) SOOECI_LS.COMM = "";
        JIBS_tmp_int = SOOECI_LS.COMM.length();
        for (int i=0;i<30000-JIBS_tmp_int;i++) SOOECI_LS.COMM += " ";
        IBS.CPY2CLS(SCCGWA, SOOECI_LS.COMM.substring(0, WS_VARS.I), FILLER47.SOCIOA);
        IBS.init(SCCGWA, SCCSCHM);
        SCCSCHM.BANK_ID = "" + FILLER47.SOCIOA.TR_BANK;
        JIBS_tmp_int = SCCSCHM.BANK_ID.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) SCCSCHM.BANK_ID = "0" + SCCSCHM.BANK_ID;
        SCZSCHM SCZSCHM2 = new SCZSCHM();
        SCZSCHM2.MP(SCCGWA, SCCSCHM);
        if (SCCSCHM.RC != ' ') {
            IBS.init(SCCGWA, SCCEXCP);
            df = new DecimalFormat("0");
            WS_VARS.SQLCODE_E = df.format(SCCSCHM.SQLCODE);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "SCHM ERROR, SQLCODE=" + WS_VARS.SQLCODE_E;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.init(SCCGWA, SCCITRC);
        SCCITRC.FUNC = '1';
        SCCITRC.TR_ID = FILLER47.SOCIOA.TR_ID;
        SCCITRC.TL_ID = FILLER47.SOCIOA.TL_ID;
        SCZITRC SCZITRC3 = new SCZITRC();
        SCZITRC3.MP(SCCGWA, SCCITRC);
        WS_VARS.LEN = 309848 - 10240;
        CEP.TRC(SCCGWA, 1);
        CEP.TRC(SCCGWA, WS_VARS.BEGIN_TS);
        CEP.TRC(SCCGWA, 1);
