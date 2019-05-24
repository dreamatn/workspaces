package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4080 {
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BP215";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSENTY BPCSENTY = new BPCSENTY();
    SCCGWA SCCGWA;
    BPB4051_AWA_4051 BPB4051_AWA_4051;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4080 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4051_AWA_4051>");
        BPB4051_AWA_4051 = (BPB4051_AWA_4051) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSENTY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if ((BPB4051_AWA_4051.MODNO.trim().length() > 0 
            || BPB4051_AWA_4051.CNTR_TYP.trim().length() > 0 
            && BPB4051_AWA_4051.PROD_TYP.trim().length() > 0 
            && !BPB4051_AWA_4051.PROD_TYP.equalsIgnoreCase("*") 
            && BPB4051_AWA_4051.BR != 0 
            && BPB4051_AWA_4051.BR != 999999) 
            && (BPB4051_AWA_4051.EVT_TYPE.trim().length() > 0 
            && BPB4051_AWA_4051.GL_BOOK.trim().length() > 0)) {
            BPCSENTY.INPUT_DATA.FUNC = 'I';
        } else {
            BPCSENTY.INPUT_DATA.FUNC = 'B';
        }
        CEP.TRC(SCCGWA, BPB4051_AWA_4051.CNTR_TYP);
        CEP.TRC(SCCGWA, BPB4051_AWA_4051.PROD_TYP);
        CEP.TRC(SCCGWA, BPB4051_AWA_4051.BR);
        CEP.TRC(SCCGWA, BPB4051_AWA_4051.MODNO);
        CEP.TRC(SCCGWA, BPB4051_AWA_4051.EVT_TYPE);
        CEP.TRC(SCCGWA, BPB4051_AWA_4051.GL_BOOK);
        BPCSENTY.INPUT_DATA.CNTR_TYPE = BPB4051_AWA_4051.CNTR_TYP;
        if (BPB4051_AWA_4051.PROD_TYP.equalsIgnoreCase("*")) {
            BPCSENTY.INPUT_DATA.PROD_TYPE = " ";
        } else {
            BPCSENTY.INPUT_DATA.PROD_TYPE = BPB4051_AWA_4051.PROD_TYP;
        }
        if (BPB4051_AWA_4051.BR == 999999) {
            BPCSENTY.INPUT_DATA.BR_AC = 0;
        } else {
            BPCSENTY.INPUT_DATA.BR_AC = BPB4051_AWA_4051.BR;
        }
        BPCSENTY.INPUT_DATA.MODNO = BPB4051_AWA_4051.MODNO;
        BPCSENTY.INPUT_DATA.EVENT_TYPE = BPB4051_AWA_4051.EVT_TYPE;
        BPCSENTY.INPUT_DATA.GL_BOOK = BPB4051_AWA_4051.GL_BOOK;
        S000_CALL_BPZSENTY();
    }
    public void S000_CALL_BPZSENTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-ENTY", BPCSENTY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
