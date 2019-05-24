package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZBOPC {
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_CI_NO = " ";
    String WS_RCI_NO = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    char WS_BAS_FOUND_FLG = ' ';
    char WS_OPEN_ID_CHK_FLG = ' ';
    char WS_ID_EXP_FLG = ' ';
    char WS_INFO_LACK_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRPDM CIRPDM = new CIRPDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRRISK CIRRISK = new CIRRISK();
    CIRCRS CIRCRS = new CIRCRS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCINO CICCINO = new CICCINO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICOBOPC CICOBOPC = new CICOBOPC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBOPC CICBOPC;
    public void MP(SCCGWA SCCGWA, CICBOPC CICBOPC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBOPC = CICBOPC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBOPC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBOPC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        B030_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B040_WRITE_ID_INFO();
        if (pgmRtn) return;
        B050_WRITE_NAM_INFO();
        if (pgmRtn) return;
        B060_WRITE_ADR_INFO();
        if (pgmRtn) return;
        B070_WRITE_CNT_INFO();
        if (pgmRtn) return;
        B080_WRITE_RISK_INFO();
        if (pgmRtn) return;
        B090_WRITE_HISTORY();
        if (pgmRtn) return;
        B100_OUTPUT_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICBOPC.DATA.ID_TYPE.trim().length() == 0 
            || CICBOPC.DATA.ID_NO.trim().length() == 0 
            || CICBOPC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        if (CICBOPC.DATA.RESIDENT == ' ' 
            || CICBOPC.DATA.SEX == ' ' 
            || CICBOPC.DATA.BIRTH_DT == 0 
            || CICBOPC.DATA.REG_CNTY.trim().length() == 0 
            || CICBOPC.DATA.NATION.trim().length() == 0 
            || CICBOPC.DATA.VER_FLG == ' ' 
            || CICBOPC.DATA.SID_FLG == ' ' 
            || CICBOPC.DATA.PER_INC == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICBOPC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICBOPC.DATA.ID_NO;
