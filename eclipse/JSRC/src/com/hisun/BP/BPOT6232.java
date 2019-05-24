package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6232 {
    String K_CPN_S_MAINTAIN_TWND = "BP-S-MAINTAIN-TWND  ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP747";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTWND BPCSTWND = new BPCSTWND();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6230_AWA_6230 BPB6230_AWA_6230;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6232 return!");
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
        if (BPB6230_AWA_6230.EFF_DT != BPB6230_AWA_6230.WND_DATE) {
            BPCSTWND.FUNC = 'M';
        } else {
            BPCSTWND.FUNC = 'U';
        }
        BPCSTWND.KEY.CAL_CD = BPB6230_AWA_6230.CAL_CD;
        BPCSTWND.KEY.EFF_DATE = BPB6230_AWA_6230.EFF_DT;
        BPCSTWND.DATE = BPB6230_AWA_6230.WND_DATE;
        BPCSTWND.HOL_DATA[1-1].WND_NO = BPB6230_AWA_6230.WND_NO1;
        BPCSTWND.HOL_DATA[2-1].WND_NO = BPB6230_AWA_6230.WND_NO2;
        BPCSTWND.HOL_DATA[3-1].WND_NO = BPB6230_AWA_6230.WND_NO3;
        BPCSTWND.HOL_DATA[4-1].WND_NO = BPB6230_AWA_6230.WND_NO4;
        BPCSTWND.HOL_DATA[5-1].WND_NO = BPB6230_AWA_6230.WND_NO5;
        BPCSTWND.HOL_DATA[6-1].WND_NO = BPB6230_AWA_6230.WND_NO6;
        BPCSTWND.HOL_DATA[7-1].WND_NO = BPB6230_AWA_6230.WND_NO7;
        BPCSTWND.HOL_DATA[1-1].WND_OPT = BPB6230_AWA_6230.WND_OPT1;
        BPCSTWND.HOL_DATA[2-1].WND_OPT = BPB6230_AWA_6230.WND_OPT2;
        BPCSTWND.HOL_DATA[3-1].WND_OPT = BPB6230_AWA_6230.WND_OPT3;
        BPCSTWND.HOL_DATA[4-1].WND_OPT = BPB6230_AWA_6230.WND_OPT4;
        BPCSTWND.HOL_DATA[5-1].WND_OPT = BPB6230_AWA_6230.WND_OPT5;
        BPCSTWND.HOL_DATA[6-1].WND_OPT = BPB6230_AWA_6230.WND_OPT6;
        BPCSTWND.HOL_DATA[7-1].WND_OPT = BPB6230_AWA_6230.WND_OPT7;
        BPCSTWND.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSTWND.LAST_TELLER = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[1-1].WND_OPT);
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[2-1].WND_OPT);
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[3-1].WND_OPT);
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[4-1].WND_OPT);
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[5-1].WND_OPT);
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[6-1].WND_OPT);
        CEP.TRC(SCCGWA, BPCSTWND.HOL_DATA[7-1].WND_OPT);
        S010_CALL_BPZSTWND();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6230_AWA_6230.EFF_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR;
            WS_FLD_NO = BPB6230_AWA_6230.EFF_DT_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6230_AWA_6230.EFF_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6230_AWA_6230.EFF_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSTWND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_MAINTAIN_TWND, BPCSTWND);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIQCNT);
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
        CEP.TRC(SCCGWA, BPCIQCNT.RC.RC_CODE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
