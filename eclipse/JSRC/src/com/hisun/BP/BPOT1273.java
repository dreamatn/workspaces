package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1273 {
    String CPN_S_BPZFFCLT = "BP-F-S-FEE-COLLECT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_CALL_DCZUSPAC = "DC-INQ-STD-AC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_HOLI_MTH = ' ';
    char WS_CAL_CYC = ' ';
    char WS_SGN_RNG = ' ';
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFCLT BPCSFCLT = new BPCSFCLT();
    CICACCU CICACCU = new CICACCU();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1270_AWA_1270 BPB1270_AWA_1270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1273 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1270_AWA_1270>");
        BPB1270_AWA_1270 = (BPB1270_AWA_1270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFCLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MOD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1270_AWA_1270.EFF_DATE > BPB1270_AWA_1270.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            WS_FLD_NO = BPB1270_AWA_1270.EXP_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1270_AWA_1270.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_ACDATE;
            WS_FLD_NO = BPB1270_AWA_1270.EXP_DATE_NO;
            S000_ERR_MSG_PROC();
        }
        WS_SGN_RNG = BPB1270_AWA_1270.SGN_RNG;
        if ((WS_SGN_RNG != 'A' 
            && WS_SGN_RNG != 'S')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_HOLI_MTH_ERR;
            WS_FLD_NO = BPB1270_AWA_1270.SGN_RNG_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB1270_AWA_1270.SGN_RNG == 'S' 
            && BPB1270_AWA_1270.FEE_CDS[1-1].FEE_CD1.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_1_FE_CD_AT_LEAST;
            WS_FLD_NO = BPB1270_AWA_1270.SGN_RNG_NO;
            S000_ERR_MSG_PROC();
        }
        WS_HOLI_MTH = BPB1270_AWA_1270.HOLI_MTH;
        if ((WS_HOLI_MTH != 'F' 
            && WS_HOLI_MTH != 'M' 
            && WS_HOLI_MTH != 'P' 
            && WS_HOLI_MTH != 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_HOLI_MTH_ERR;
            WS_FLD_NO = BPB1270_AWA_1270.HOLI_MTH_NO;
            S000_ERR_MSG_PROC();
        }
        WS_CAL_CYC = BPB1270_AWA_1270.CAL_CYC;
        if ((WS_CAL_CYC != 'D' 
            && WS_CAL_CYC != 'W' 
            && WS_CAL_CYC != 'M' 
            && WS_CAL_CYC != 'Y')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_CAL_CYC_ERR;
            WS_FLD_NO = BPB1270_AWA_1270.CAL_CYC_NO;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.SGN_TYPE);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.SGN_AC);
        CEP.TRC(SCCGWA, BPB1270_AWA_1270.CHG_AC);
        if (BPB1270_AWA_1270.SGN_TYPE == '1' 
            && !BPB1270_AWA_1270.SGN_AC.equalsIgnoreCase(BPB1270_AWA_1270.CHG_AC)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.E_AC_MUST_BE_SGN;
            WS_FLD_NO = BPB1270_AWA_1270.CHG_AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MOD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFCLT);
        BPCSFCLT.CLT_TYPE = BPB1270_AWA_1270.CLT_TYPE;
        BPCSFCLT.SGN_TYPE = BPB1270_AWA_1270.SGN_TYPE;
        BPCSFCLT.SGN_CINO = BPB1270_AWA_1270.SGN_CINO;
        BPCSFCLT.SGN_AC = BPB1270_AWA_1270.SGN_AC;
        BPCSFCLT.EFF_DATE = BPB1270_AWA_1270.EFF_DATE;
        BPCSFCLT.EXP_DATE = BPB1270_AWA_1270.EXP_DATE;
        BPCSFCLT.CHG_AC = BPB1270_AWA_1270.CHG_AC;
        BPCSFCLT.CCY = BPB1270_AWA_1270.CCY;
        BPCSFCLT.CAL_CYC = BPB1270_AWA_1270.CAL_CYC;
        BPCSFCLT.PERD_CNT = BPB1270_AWA_1270.PERD_CNT;
        BPCSFCLT.HOLI_MTH = BPB1270_AWA_1270.HOLI_MTH;
        BPCSFCLT.HLD_NO = BPB1270_AWA_1270.HLD_NO;
        BPCSFCLT.REMARK = BPB1270_AWA_1270.REMARK;
        BPCSFCLT.SGN_RNG = BPB1270_AWA_1270.SGN_RNG;
        for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
            BPCSFCLT.FEE_CDS[WS_CNT-1].FEE_CD1 = BPB1270_AWA_1270.FEE_CDS[WS_CNT-1].FEE_CD1;
        }
        BPCSFCLT.FUNC = 'U';
        S00_CALL_BPZFFCLT();
    }
    public void B013_CHECK_SPAC_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUSPAC);
        DCCUSPAC.FUNC.AC = BPB1270_AWA_1270.CHG_AC;
        S000_CALL_DCZUSPAC();
        if (DCCUSPAC.OUTPUT.STD_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.ENTY_TYP = '1';
            CICACCU.DATA.AGR_NO = DCCUSPAC.OUTPUT.STD_AC;
            CEP.TRC(SCCGWA, "TESTING-INPUT AC SECOND");
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            S000_CALL_CIZACCU();
        }
    }
    public void S000_CALL_DCZUSPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_DCZUSPAC, DCCUSPAC);
    }
    public void S00_CALL_BPZFFCLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BPZFFCLT, BPCSFCLT);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
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
