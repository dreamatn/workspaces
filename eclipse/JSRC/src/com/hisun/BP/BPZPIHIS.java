package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPIHIS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_FHIST_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHISA BPRFHISA = new BPRFHISA();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    BPCPIHIS BPCPIHIS;
    public void MP(SCCGWA SCCGWA, BPCPIHIS BPCPIHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPIHIS = BPCPIHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPIHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCIFHSA);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPIHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_LAST_DATE_BALANCE();
        if (pgmRtn) return;
        B030_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPIHIS);
        if (BPCPIHIS.DATA.KEY.AC.equalsIgnoreCase("0") 
            || BPCPIHIS.DATA.KEY.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCPIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPIHIS.DATA.KEY.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT, BPCPIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_LAST_DATE_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHSA);
        BPCIFHSA.DATA.FUNC = '4';
        BPCIFHSA.DATA.AC = BPCPIHIS.DATA.KEY.AC;
        BPCIFHSA.DATA.CCY = BPCPIHIS.DATA.KEY.CCY;
        BPCIFHSA.DATA.AC_DT = BPCPIHIS.DATA.KEY.AC_DT;
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        BPCIFHSA.DATA.REC_LEN = 144;
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        BPCPIHIS.DATA.KEY.AC_DT = BPRFHISA.AC_DT;
        BPCPIHIS.DATA.CUR_BAL = BPRFHISA.CUR_BAL;
        BPCPIHIS.DATA.L_BAL = BPRFHISA.L_BAL;
        BPCPIHIS.DATA.L_STMTNO = BPRFHISA.L_STMTNO;
        BPCPIHIS.DATA.PT_STMTNO = BPRFHISA.PT_STMTNO;
        BPCPIHIS.DATA.TDAY_SUM = BPRFHISA.TDAY_SUM;
    }
    public void S000_CALL_BPZIFHSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHISA", BPCIFHSA);
        CEP.TRC(SCCGWA, BPCIFHSA.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPIHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPIHIS = ");
            CEP.TRC(SCCGWA, BPCPIHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
