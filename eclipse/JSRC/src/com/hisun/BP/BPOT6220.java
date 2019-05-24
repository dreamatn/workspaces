package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6220 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTHOL BPCSTHOL = new BPCSTHOL();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB6220_AWA_6220 BPB6220_AWA_6220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6220 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6220_AWA_6220>");
        BPB6220_AWA_6220 = (BPB6220_AWA_6220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_HOL_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.EFF_DT);
        if (BPB6220_AWA_6220.EFF_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6220_AWA_6220.EFF_DT;
            JIBS_tmp_str[9] = "SCSSCKDT";
            Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
            Object obj = clazz.newInstance();
            Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCCKDT.getClass()});
            m.invoke(obj, SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6220_AWA_6220.EFF_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_HOL_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.EFF_DT);
        IBS.init(SCCGWA, BPCSTHOL);
        BPCSTHOL.KEY.CAL_CD = BPB6220_AWA_6220.CAL_CD;
        BPCSTHOL.KEY.EFF_DT = BPB6220_AWA_6220.EFF_DT;
        BPCSTHOL.FUNC = 'B';
        CEP.TRC(SCCGWA, BPCSTHOL.KEY);
        CEP.TRC(SCCGWA, BPCSTHOL.FUNC);
        S000_CALL_BPZSTHOL();
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-CNTY-CITY-IFO", BPCQCNCI);
    }
    public void S000_CALL_BPZSTHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINTAIN-THOL", BPCSTHOL);
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
