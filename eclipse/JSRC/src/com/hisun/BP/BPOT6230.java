package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6230 {
    String JIBS_tmp_str[] = new String[10];
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTWND BPCSTWND = new BPCSTWND();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6230_AWA_6230 BPB6230_AWA_6230;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6230_AWA_6230>");
        BPB6230_AWA_6230 = (BPB6230_AWA_6230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSTWND);
        BPCSTWND.FUNC = 'B';
        BPCSTWND.KEY.EFF_DATE = BPB6230_AWA_6230.EFF_DT;
        BPCSTWND.KEY.CAL_CD = BPB6230_AWA_6230.CAL_CD;
        S010_CALL_BPZSTWND();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6230_AWA_6230.EFF_DT);
        if (BPB6230_AWA_6230.EFF_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6230_AWA_6230.EFF_DT;
            JIBS_tmp_str[9] = "SCSSCKDT";
            Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
            Object obj = clazz.newInstance();
            Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCCKDT.getClass()});
            m.invoke(obj, SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6230_AWA_6230.EFF_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-CNTY-CITY-IFO", BPCQCNCI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSTWND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINTAIN-TWND", BPCSTWND);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
