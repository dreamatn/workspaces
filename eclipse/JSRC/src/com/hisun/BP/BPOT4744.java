package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4744 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_AGC_MAINTAIN = "BP-S-MAINT-AUTOGEN";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4744_WS_ERR_MSG WS_ERR_MSG = new BPOT4744_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4744_REDEFINES8 REDEFINES8 = new BPOT4744_REDEFINES8();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    BPOT4744_WS_MON WS_MON = new BPOT4744_WS_MON();
    char WS_DT_TYP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSAGCA BPCSAGCA = new BPCSAGCA();
    SCCGWA SCCGWA;
    BPB4730_AWA_4730 BPB4730_AWA_4730;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4744 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4730_AWA_4730>");
        BPB4730_AWA_4730 = (BPB4730_AWA_4730) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B200_MAIN_PROCESS();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSAGCA);
        BPCSAGCA.FUNC = 'D';
        BPCSAGCA.OUTPUT_FLG = 'N';
        BPCSAGCA.CAL_CODE = BPB4730_AWA_4730.CALCD;
        BPCSAGCA.CAL_NAME = BPB4730_AWA_4730.CAL_NAME;
        BPCSAGCA.B_CAL_CODE = BPB4730_AWA_4730.T_CALCD;
        BPCSAGCA.B_CAL_NAME = BPB4730_AWA_4730.T_CAL_NE;
        BPCSAGCA.YEAR = BPB4730_AWA_4730.YEAR;
        BPCSAGCA.EFF_DATE = BPB4730_AWA_4730.EFF_DATE;
        BPCSAGCA.EXP_DATE = BPB4730_AWA_4730.EXP_DATE;
        S000_CALL_BPZSAGCA();
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_AP = "SC";
            WS_ERR_MSG.WS_ERR_CODE = SCCCKDT.RC;
            WS_FLD_NO = WS_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSAGCA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_AGC_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSAGCA;
        SCCCALL.ERR_FLDNO = BPB4730_AWA_4730.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
