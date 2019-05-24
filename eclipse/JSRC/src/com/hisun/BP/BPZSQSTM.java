package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class BPZSQSTM {
    boolean pgmRtn = false;
    String K_CMP_MAIN_BPTDIARY = "BP-R-MAINT-UPDATA";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    double WS_TEMP_BALANCE = 0;
    char WS_FHIST_FLG = ' ';
    char WS_STOP = ' ';
    BPZSQSTM_WS_STATEMENT_DATA WS_STATEMENT_DATA = new BPZSQSTM_WS_STATEMENT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    BPRFHISA BPRFHISA = new BPRFHISA();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSQSTM BPCSQSTM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSQSTM BPCSQSTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQSTM = BPCSQSTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSQSTM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSQSTM.FUNC == 'Q') {
            B010_ENQ_STATEMENT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSQSTM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_ENQ_STATEMENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 186;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCIFHSA);
        IBS.init(SCCGWA, BPRFHISA);
        IBS.init(SCCGWA, WS_STATEMENT_DATA);
        BPCIFHSA.DATA.FUNC = '1';
        BPCIFHSA.DATA.AC = BPCSQSTM.ACNO;
        BPCIFHSA.DATA.CCY = BPCSQSTM.CCY;
        BPCIFHSA.DATA.AC_DT = BPCSQSTM.FROM_DATE;
        BPCIFHSA.DATA.REC_LEN = 144;
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        S00_CALL_BPZIFHSA();
        if (pgmRtn) return;
        BPCIFHSA.DATA.FUNC = '2';
        S00_CALL_BPZIFHSA();
        if (pgmRtn) return;
        WS_STATEMENT_DATA.WS_TX_DATE = BPCSQSTM.FROM_DATE;
        WS_STATEMENT_DATA.WS_VAL_DATE = BPCSQSTM.FROM_DATE;
        WS_STATEMENT_DATA.WS_PARTICULARS = "OPENING BALANCE";
        WS_STATEMENT_DATA.WS_BALANCE = BPRFHISA.CUR_BAL;
        WS_TEMP_BALANCE = BPRFHISA.CUR_BAL;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_STATEMENT_DATA);
        SCCMPAG.DATA_LEN = 186;
        B_MPAG();
        if (pgmRtn) return;
        BPCIFHSA.DATA.FUNC = '3';
        S00_CALL_BPZIFHSA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCIFHIS);
        BPCIFHIS.INPUT.AC = BPCSQSTM.ACNO;
        BPCIFHIS.INPUT.TX_CCY = BPCSQSTM.CCY;
        BPCIFHIS.INPUT.STR_AC_DT = BPCSQSTM.FROM_DATE;
        BPCIFHIS.INPUT.END_AC_DT = BPCSQSTM.TO_DATE;
        BPCIFHIS.INPUT.FUNC = '7';
        BPCIFHIS.INPUT.REC_LEN = 690;
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        BPCIFHIS.INPUT.FUNC = '2';
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        WS_STOP = 'N';
        while (WS_FHIST_FLG != 'N' 
            && WS_STOP != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            if (BPRFHIST.PRINT_FLG != 'N') {
                R000_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            }
            BPCIFHIS.INPUT.FUNC = '2';
            S000_CALL_BPZIFHIS();
            if (pgmRtn) return;
        }
        BPCIFHIS.INPUT.FUNC = '3';
        S000_CALL_BPZIFHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BROWSE END");
        IBS.init(SCCGWA, WS_STATEMENT_DATA);
        WS_STATEMENT_DATA.WS_TX_DATE = BPCSQSTM.TO_DATE;
        WS_STATEMENT_DATA.WS_VAL_DATE = BPCSQSTM.TO_DATE;
        WS_STATEMENT_DATA.WS_PARTICULARS = "CLOSING BALANCE";
        WS_STATEMENT_DATA.WS_BALANCE = WS_TEMP_BALANCE;
        CEP.TRC(SCCGWA, WS_TEMP_BALANCE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_STATEMENT_DATA);
        SCCMPAG.DATA_LEN = 186;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT");
        IBS.init(SCCGWA, WS_STATEMENT_DATA);
        WS_STATEMENT_DATA.WS_TX_DATE = BPRFHIST.KEY.AC_DT;
        WS_STATEMENT_DATA.WS_VAL_DATE = BPRFHIST.TX_VAL_DT;
        WS_STATEMENT_DATA.WS_PARTICULARS = BPRFHIST.NARRATIVE;
