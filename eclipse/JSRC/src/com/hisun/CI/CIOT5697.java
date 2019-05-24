package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIOT5697 {
    boolean pgmRtn = false;
    char K_STE_TYP_AC = '1';
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    String WS_STD_AC = " ";
    char WS_ACR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMSTCE CICMSTCE = new CICMSTCE();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CIRACR CIRACR = new CIRACR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB5690_AWA_5690 CIB5690_AWA_5690;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT5697 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB5690_AWA_5690>");
        CIB5690_AWA_5690 = (CIB5690_AWA_5690) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICMSTCE);
        CICMSTCE.DATA.CI_NO = CIB5690_AWA_5690.CI_NO;
        CICMSTCE.DATA.STE_TYPE = CIB5690_AWA_5690.STE_TYPE;
        CICMSTCE.DATA.AC = WS_STD_AC;
        CICMSTCE.FUNC = 'I';
        S000_CALL_CIZMSTCE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CIB5690_AWA_5690.CI_NO.trim().length() == 0) {
            WS_MSGID = CICMSG_ERROR_MSG.CI_NO_MUST_INPUT;
            WS_FLD_NO = CIB5690_AWA_5690.CI_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (CIB5690_AWA_5690.STE_TYPE == ' ') {
            WS_MSGID = CICMSG_ERROR_MSG.CI_STE_TYP_MUST_INPUT;
            WS_FLD_NO = CIB5690_AWA_5690.STE_TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (CIB5690_AWA_5690.STE_TYPE == K_STE_TYP_AC 
                && CIB5690_AWA_5690.AC.trim().length() == 0) {
                WS_MSGID = CICMSG_ERROR_MSG.CI_AC_MUST_INPUT;
                WS_FLD_NO = CIB5690_AWA_5690.AC_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (CIB5690_AWA_5690.AC.compareTo(0) > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIB5690_AWA_5690.AC;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (WS_ACR_FLG == 'Y') {
                WS_STD_AC = CIB5690_AWA_5690.AC;
            } else {
                IBS.init(SCCGWA, DCCPACTY);
                DCCPACTY.INPUT.AC = CIB5690_AWA_5690.AC;
                S000_CALL_DCZPACTY();
                if (pgmRtn) return;
                if (!DCCPACTY.OUTPUT.STD_AC.equalsIgnoreCase("0")) {
                    WS_STD_AC = DCCPACTY.OUTPUT.STD_AC;
                } else {
                    WS_STD_AC = DCCPACTY.INPUT.AC;
                }
            }
        }
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.col = "AGR_NO";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACR_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMSTCE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-STCE", CICMSTCE);
        if (CICMSTCE.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMSTCE.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY, true);
        if (DCCPACTY.RC.RC_CODE != 0) {
